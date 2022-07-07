package ru.otus.spring.spring05.dao;

import ru.otus.spring.spring05.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    Book add(Book book);

    void update(Book book);

    void delete(Book book);

    Book getById(long id);

    List<Book> getAll();
}
