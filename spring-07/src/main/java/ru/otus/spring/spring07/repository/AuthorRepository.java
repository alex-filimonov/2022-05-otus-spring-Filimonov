package ru.otus.spring.spring07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring07.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    
}
