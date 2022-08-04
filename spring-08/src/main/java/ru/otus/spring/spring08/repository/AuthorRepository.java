package ru.otus.spring.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.spring08.domain.Author;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AuthorRepository  extends MongoRepository<Author, String> {
    public Optional<Author> findById(int id);
}
