package com.example.app.ports;

import com.example.adapter.in.dto.FilemetadataDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CreateVideoUseCasePort {
    Mono<FilemetadataDto> createVideo(FilemetadataDto filemetadataDto);

    Mono<ResponseEntity<Resource>> downloadVideo(String nameFile);

    Mono<ResponseEntity<Resource>> streamVideo(String name);
}
