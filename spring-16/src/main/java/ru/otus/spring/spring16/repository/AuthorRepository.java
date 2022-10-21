package ru.otus.spring.spring16.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring16.domain.Author;
import ru.otus.spring.spring16.domain.Book;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findById(int id);

}
