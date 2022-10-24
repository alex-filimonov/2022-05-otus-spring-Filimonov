package ru.otus.spring.spring17.service;

import ru.otus.spring.spring17.domain.Comment;
import ru.otus.spring.spring17.dto.CommentDto;

import javax.transaction.Transactional;

public interface CommentService {
    Comment findById(int id);

    @Transactional
    Comment add(CommentDto commentDto);

    @Transactional
    void update(CommentDto commentDto);

    @Transactional
    void delete(int id);
}
