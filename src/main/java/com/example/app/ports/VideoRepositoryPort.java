package com.example.app.ports;

import com.example.adapter.in.dto.FilemetadataDto;
import reactor.core.publisher.Mono;

public interface VideoRepositoryPort {
    Mono<FilemetadataDto> saveVideo(FilemetadataDto dto);

    Mono<FilemetadataDto> getFileByName(String name);
}
