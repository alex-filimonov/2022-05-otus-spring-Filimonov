package ru.otus.spring.spring11.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAll();


}
