package ru.otus.spring.spring11.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Comment;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.BookRepository;
import ru.otus.spring.spring11.repository.CommentRepository;
import ru.otus.spring.spring11.rest.dto.CommentDto;
import ru.otus.spring.spring11.service.CommentService;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;


    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping("/api/comments/{id}")
    public Flux<CommentDto> getComments(@PathVariable String id){
        return commentService.getComments(id);
    }

    @GetMapping("/api/comment/{bookId}/{id}")
    public Mono<CommentDto> getComment(@PathVariable String bookId,@PathVariable String id){
        return commentService.getComment(bookId,id);
    }

    @PostMapping("/api/comment/{id}")
    public Mono<CommentDto> add(@PathVariable String id, @RequestBody CommentDto commentDto){
        return commentService.add(id,commentDto);
    }

    @PutMapping("/api/comment/{bookId}/{id}")
    public Mono<CommentDto> update(@PathVariable String bookId,@PathVariable String id,@RequestBody CommentDto commentDto){
        return commentService.update(bookId,id,commentDto);
    }

    @DeleteMapping("/api/comment/{bookId}/{id}")
    public Mono<Book> delete(@PathVariable String bookId,@PathVariable String id){
        return commentService.delete(bookId,id);
    }

}
