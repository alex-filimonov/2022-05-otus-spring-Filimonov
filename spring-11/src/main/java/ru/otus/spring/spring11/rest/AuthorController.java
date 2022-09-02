package ru.otus.spring.spring11.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.repository.AuthorRepository;
import ru.otus.spring.spring11.repository.BookRepository;

@RestController
public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    @GetMapping("/api/authors")
    public Flux<Author> all(){
        return authorRepository.findAll();
    }


}
