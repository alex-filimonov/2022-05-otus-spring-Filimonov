package ru.otus.spring.spring07.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.spring07.domain.Book;
import ru.otus.spring.spring07.domain.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findById(int id);
}
