package ru.otus.spring.spring06.repository;

import ru.otus.spring.spring06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(int id);
    void updateDataById(int id, String data);
    void deleteById(int id);
}
