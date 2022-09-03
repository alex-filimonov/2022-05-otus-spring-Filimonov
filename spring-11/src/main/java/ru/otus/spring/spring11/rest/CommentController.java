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

import java.util.List;

@RestController
public class CommentController {
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    public CommentController(BookRepository bookRepository,CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.commentRepository=commentRepository;
    }

    @GetMapping("/api/comments/{id}")
    public Flux<CommentDto> getComments(@PathVariable String id){
        Mono<Book> bookMono=bookRepository.findById(id);
        return bookMono.map(b-> b.getCommentList())
                .flatMapMany(Flux::fromIterable)
                .map(c->new CommentDto(c.getId(),c.getData()));
    }

    @GetMapping("/api/comment/{bookId}/{id}")
    public Mono<CommentDto> getComment(@PathVariable String bookId,@PathVariable String id){
        return commentRepository.findById(id).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @PostMapping("/api/comment/{id}")
    public Mono<CommentDto> add(@PathVariable String id, @RequestBody CommentDto commentDto){
        Mono<Book> bookMono=bookRepository.findById(id);
        Comment comment=new Comment(commentDto.getData());
        Mono<Comment> commentMono=commentRepository.save(comment);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            b.getCommentList().add(c);
            return bookRepository.save(b);
        }).map(book -> book.getCommentList().get(book.getCommentList().size()-1)).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @PutMapping("/api/comment/{bookId}/{id}")
    public Mono<CommentDto> update(@PathVariable String bookId,@PathVariable String id,@RequestBody CommentDto commentDto){
        Mono<Book> bookMono=bookRepository.findById(bookId);
        Mono<Comment> commentMono=commentRepository.findById(id);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            c.setData(commentDto.getData());
            b.getCommentList().stream().filter(it->it.getId().equals(c.getId())).findFirst().get().setData(commentDto.getData());
            commentRepository.save(c);
            return bookRepository.save(b);
        }).map(book -> book.getCommentList().stream().filter(it->it.getId().equals(id)).findFirst().get()).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @DeleteMapping("/api/comment/{bookId}/{id}")
    public Mono<Book> delete(@PathVariable String bookId,@PathVariable String id){
        Mono<Book> bookMono=bookRepository.findById(bookId);
        Mono<Comment> commentMono=commentRepository.findById(id);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            b.getCommentList().removeIf(i->i.getId().equals(id));
            commentRepository.delete(c);
            return bookRepository.save(b);
        });
    }

}
