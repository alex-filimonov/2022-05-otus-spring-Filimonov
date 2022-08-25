package ru.otus.spring.spring10.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring10.domain.Book;
import ru.otus.spring.spring10.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    Optional<Comment> findById(int id);
    void deleteById(Long id);
}
