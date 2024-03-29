package ru.otus.spring.spring10.service;

import ru.otus.spring.spring10.domain.Book;
import ru.otus.spring.spring10.dto.BookDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(Long id);

    @Transactional
    Book add(BookDto bookDto);

    @Transactional
    void update(BookDto bookDto);

    @Transactional
    void delete(Long bookId);
}
