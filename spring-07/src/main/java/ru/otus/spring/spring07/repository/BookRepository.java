package ru.otus.spring.spring07.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring07.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @EntityGraph(attributePaths = "book-genre-author-entity-graph")
    List<Book> findAll();
    Optional<Book> findById(int id);
}
