package ru.otus.spring.spring11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    public Mono<Genre> findById(int id);
}
