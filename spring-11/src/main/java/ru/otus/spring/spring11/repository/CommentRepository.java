package ru.otus.spring.spring11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment,String> {
    Mono<Comment> findById(int id);
}
