package ru.otus.spring.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.spring08.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment,String> {
}
