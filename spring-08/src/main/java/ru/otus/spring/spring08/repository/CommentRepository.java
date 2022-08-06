package ru.otus.spring.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.spring08.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment,String> {
    Optional<Comment> findById(int id);
}
