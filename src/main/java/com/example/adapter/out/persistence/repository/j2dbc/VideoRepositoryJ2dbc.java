package com.example.adapter.out.persistence.repository.j2dbc;

import com.example.adapter.out.persistence.entity.Filemetadata;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@EnableR2dbcRepositories
public interface VideoRepositoryJ2dbc extends ReactiveCrudRepository<Filemetadata, Long> {
    @Query("SELECT * FROM filemetadata WHERE name = :name")
    Mono<Filemetadata> findByName(String name);
}
