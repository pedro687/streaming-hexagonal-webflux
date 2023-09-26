package com.example.app.usecases;

import com.example.adapter.in.dto.FilemetadataDto;
import com.example.app.ports.CreateVideoUseCasePort;
import com.example.app.ports.VideoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CreateVideoUseCase implements CreateVideoUseCasePort {

    @Autowired
    private VideoRepositoryPort videoRepositoryPort;

    @Override
    public Mono<FilemetadataDto> createVideo(FilemetadataDto filemetadataDto) {
        return videoRepositoryPort.saveVideo(filemetadataDto);
    }

    @Override
    public Mono<ResponseEntity<Resource>> downloadVideo(String nameFile) {
        return videoRepositoryPort.getFileByName(nameFile)
                .flatMap(fileMetadata -> {
                    try {
                        Path uploadsPath = Paths.get("uploads").toAbsolutePath();
                        Path filePath = uploadsPath.resolve(uploadsPath.resolve(fileMetadata.getPath()));

                        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath.toString()));

                        return Mono.just(ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileMetadata.getName())
                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                .body(resource));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
    }

    @Override
    public Mono<ResponseEntity<Resource>> streamVideo(String name) {
        return videoRepositoryPort.getFileByName(name)
                .flatMap(fileMetadata -> {
                    Path uploadsPath = Paths.get("uploads").toAbsolutePath();
                    Path filePath = uploadsPath.resolve(uploadsPath.resolve(fileMetadata.getPath()));
                    Resource videoResource = new FileSystemResource(filePath);

                    if (videoResource.exists() && videoResource.isReadable()) {
                        return Mono.just(ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                .body(videoResource));
                    }
                    return Mono.just(ResponseEntity.notFound().build());
                });
    }
}
