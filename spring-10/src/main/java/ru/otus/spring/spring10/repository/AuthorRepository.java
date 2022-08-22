package ru.otus.spring.spring10.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring10.domain.Author;
import ru.otus.spring.spring10.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Optional<Author> findById(int id);

}
