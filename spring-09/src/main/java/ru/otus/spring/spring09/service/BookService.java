package ru.otus.spring.spring09.service;

import ru.otus.spring.spring09.domain.Book;
import ru.otus.spring.spring09.dto.BookDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(int id);

    @Transactional
    Book add(BookDto bookDto);

    @Transactional
    void update(int bookId, String bookName, int authorId, int genreId);

    @Transactional
    void delete(int bookId);
}
