package ru.otus.spring.spring06.repository;

import ru.otus.spring.spring06.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(int id);
    List<Book> findAll();
    void updateNameById(int id, String name);
    void deleteById(int id);
}
