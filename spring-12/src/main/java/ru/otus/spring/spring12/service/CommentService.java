package ru.otus.spring.spring12.service;

import ru.otus.spring.spring12.domain.Comment;
import ru.otus.spring.spring12.dto.CommentDto;

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
