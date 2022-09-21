package ru.otus.spring.spring11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Author;


public interface AuthorRepository  extends ReactiveMongoRepository<Author, String> {
    public Mono<Author> findById(int id);
}
