package ru.otus.spring.spring07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring07.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
