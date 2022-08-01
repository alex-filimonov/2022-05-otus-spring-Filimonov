package ru.otus.spring.spring06.repository;

import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(int id);
    List<Genre> findAll();
    void updateNameById(int id, String name);
    void deleteById(int id);
}
