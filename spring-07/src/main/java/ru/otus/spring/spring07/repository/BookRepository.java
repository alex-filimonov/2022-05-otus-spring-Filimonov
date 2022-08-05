package ru.otus.spring.spring07.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring07.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    Optional<Book> findById(int id);
    void deleteById(int id);
}
