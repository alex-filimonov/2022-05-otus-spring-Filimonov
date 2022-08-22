package ru.otus.spring.spring10.service;

import ru.otus.spring.spring10.domain.Comment;
import ru.otus.spring.spring10.dto.CommentDto;

import javax.transaction.Transactional;

public interface CommentService {
    Comment findById(Long id);

    @Transactional
    Comment add(CommentDto commentDto);

    @Transactional
    void update(CommentDto commentDto);

    @Transactional
    void delete(Long id);
}
