package com.example.adapter.in.controller;

import com.example.adapter.in.dto.FilemetadataDto;
import com.example.app.ports.CreateVideoUseCasePort;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final CreateVideoUseCasePort createVideoUseCasePort;


    public VideoController(CreateVideoUseCasePort createVideoUseCasePort) {
        this.createVideoUseCasePort = createVideoUseCasePort;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Void> handleFileUpload(@RequestPart("file") FilePart file) throws IOException {
        String filename = StringUtils.cleanPath(file.filename());
        String uuid = UUID.randomUUID().toString();
        String path = uuid + "/" + filename;
        FilemetadataDto fileMetadata = new FilemetadataDto();
        fileMetadata.setName(filename);
        fileMetadata.setPath(path);
        fileMetadata.setGenre("Comédia");

        return createVideoUseCasePort.createVideo(fileMetadata).then(Mono.defer(() -> {
            File directory = new File("uploads/" + uuid);
            directory.mkdirs();
            File dest = new File(directory, filename);
            return file.transferTo(dest);
        }));
    }

    @GetMapping(value = "/download/{name}")
    public Mono<ResponseEntity<Resource>> downloadByName(@PathVariable(value = "name") String name) {
        return createVideoUseCasePort.downloadVideo(name);
    }

    @GetMapping(value = "/stream/{name}")
    public Mono<ResponseEntity<Resource>> streamVideo(@PathVariable String name) {
        return createVideoUseCasePort.streamVideo(name);
    }

}

