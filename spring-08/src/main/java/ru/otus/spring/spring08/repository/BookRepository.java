package ru.otus.spring.spring08.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.spring08.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    public Optional<Book> findById(int id);
    public void deleteById(int id);
}
