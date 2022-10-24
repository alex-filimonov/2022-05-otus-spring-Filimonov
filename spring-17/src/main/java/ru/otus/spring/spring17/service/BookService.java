package ru.otus.spring.spring17.service;

import ru.otus.spring.spring17.domain.Book;
import ru.otus.spring.spring17.dto.BookDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(int id);

    @Transactional
    Book add(BookDto bookDto);

    @Transactional
    void update(BookDto bookDto);

    @Transactional
    void delete(int bookId);
}
