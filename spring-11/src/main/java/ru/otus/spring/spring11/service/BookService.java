package ru.otus.spring.spring11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.rest.dto.BookDto;

public interface BookService {
    Flux<BookDto> all();

    Mono<BookDto> get(String id);

    Mono<BookDto> add(Mono<BookDto> bookDtoMono);

    Mono<BookDto> update(String id, BookDto bookDto);

    Mono<Void> delete(String id);
}
