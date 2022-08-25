package ru.otus.spring.spring10.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.spring10.domain.Author;
import ru.otus.spring.spring10.domain.Book;
import ru.otus.spring.spring10.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
    Optional<Genre> findById(int id);
}
