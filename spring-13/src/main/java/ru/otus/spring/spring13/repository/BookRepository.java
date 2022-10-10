package ru.otus.spring.spring13.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.spring13.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Book> findAll();
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Book findById(int id);

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasPermission(#book, 'WRITE') or hasRole('ROLE_ADMIN')")
    Book save(@Param("book") Book book);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void deleteById(int id);
}
