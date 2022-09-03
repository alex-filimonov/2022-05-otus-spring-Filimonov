package ru.otus.spring.spring11.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Comment;
import ru.otus.spring.spring11.repository.BookRepository;

public class CommentController {
    private BookRepository bookRepository;

    public CommentController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/api/comment/{id}")
    public Flux<Comment> getComments(@PathVariable String id){
        Mono<Book> bookMono=bookRepository.findById(id);

    }

}
