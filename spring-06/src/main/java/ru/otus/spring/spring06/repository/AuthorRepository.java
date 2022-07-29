package ru.otus.spring.spring06.repository;

import ru.otus.spring.spring06.models.Author;
import ru.otus.spring.spring06.models.Genre;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(int id);
    List<Author> findAll();
    void updateNameById(int id, String name);
    void deleteById(int id);
}
