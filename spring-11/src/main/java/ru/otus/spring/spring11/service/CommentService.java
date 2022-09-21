package ru.otus.spring.spring11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.rest.dto.CommentDto;

public interface CommentService {
    Flux<CommentDto> getComments(String id);

    Mono<CommentDto> getComment(String bookId, String id);

    Mono<CommentDto> add(String id, CommentDto commentDto);

    Mono<CommentDto> update(String bookId, String id, CommentDto commentDto);

    Mono<Book> delete(String bookId, String id);
}
