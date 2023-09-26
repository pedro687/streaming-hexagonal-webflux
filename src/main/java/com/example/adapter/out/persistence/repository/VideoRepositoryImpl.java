package com.example.adapter.out.persistence.repository;

import com.example.adapter.in.dto.FilemetadataDto;
import com.example.adapter.out.persistence.entity.Filemetadata;
import com.example.adapter.out.persistence.repository.j2dbc.VideoRepositoryJ2dbc;
import com.example.app.ports.VideoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;

@Component
public class VideoRepositoryImpl implements VideoRepositoryPort {

    @Autowired
    private VideoRepositoryJ2dbc videoRepositoryJ2dbc;

    @Override
    public Mono<FilemetadataDto> saveVideo(FilemetadataDto dto) {
        Filemetadata filemetadata = new Filemetadata();
        filemetadata.setName(dto.getName());
        filemetadata.setPath(dto.getPath());
        filemetadata.setGenre(dto.getGenre());
        return videoRepositoryJ2dbc.save(filemetadata)
                .map(filemetadata1 -> new FilemetadataDto(filemetadata1.getName(), filemetadata1.getPath(), filemetadata1.getGenre()));
    }

    @Override
    public Mono<FilemetadataDto> getFileByName(String name) {
        return videoRepositoryJ2dbc.findByName(name)
                .map(filemetadata -> new FilemetadataDto(filemetadata.getName(), filemetadata.getPath(), filemetadata.getGenre()));
    }
}
