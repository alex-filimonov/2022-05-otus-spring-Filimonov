package ru.otus.spring.spring11.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Comment;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.AuthorRepository;
import ru.otus.spring.spring11.repository.BookRepository;
import ru.otus.spring.spring11.repository.CommentRepository;
import ru.otus.spring.spring11.repository.GenreRepository;
import ru.otus.spring.spring11.rest.dto.AuthorDto;
import ru.otus.spring.spring11.rest.dto.BookDto;
import ru.otus.spring.spring11.rest.dto.CommentDto;
import ru.otus.spring.spring11.rest.dto.GenreDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class BookController {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private CommentRepository commentRepository;



    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }

    @GetMapping("/api/books")
    public Flux<BookDto> all(){
        return bookRepository.findAll().map(this::toDto);
    }

    @GetMapping("/api/book/{id}")
    public Mono<BookDto> get(@PathVariable String id){
        return bookRepository.findById(id).map(this::toDto);
    }

    @PostMapping("/api/book")
    public Mono<BookDto> add(@RequestBody Mono<BookDto> bookDtoMono) {
        return bookDtoMono.map(bookDto -> {

            Book b=new Book(bookDto.getName(),null,null, Collections.emptyList());
            Mono<Author> authorMono=authorRepository.findById(bookDto.getAuthor().getId());
            Mono<Genre> genreMono=genreRepository.findById(bookDto.getGenre().getId());
            Mono<Book> bM=Mono.just(b);
            Mono<Tuple3<Author,Genre,Book>> tuple3Mono=Mono.zip(authorMono,genreMono,bM);

            return tuple3Mono.map(t->{
                Author a=t.getT1();
                Genre g=t.getT2();
                Book bi=t.getT3();
                bi.setAuthor(a);
                bi.setGenre(g);
                return bi;
            });
        }).flatMap(b-> b.flatMap(bv-> bookRepository.save(bv))).map(book -> this.toDto(book));
    }

    @PutMapping("/api/book/{id}")
    public Mono<BookDto> update(@PathVariable String  id, @RequestBody BookDto bookDto) {
        Mono<Author> authorMono=authorRepository.findById(bookDto.getAuthor().getId());
        Mono<Book> bookMono=bookRepository.findById(id);
        Mono<Genre> genreMono=genreRepository.findById(bookDto.getGenre().getId());
        Mono<Tuple3<Author,Book,Genre>> tuple=Mono.zip(authorMono,bookMono,genreMono);
        return tuple.map(t ->{
            Author a=t.getT1();
            Book b=t.getT2();
            Genre g=t.getT3();
            b.setAuthor(a);
            b.setGenre(g);
            b.setName(bookDto.getName());
            bookRepository.save(b);
            return b;
        }).map(book -> this.toDto(book));
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> delete(@PathVariable String id){
        Mono<Book> bookMono=bookRepository.findById(id);
        return bookMono.flatMap(b->bookRepository.delete(b));
    }


    private BookDto toDto(Book book){
        List<CommentDto> commentDtos=book.getCommentList().stream().map(c -> new CommentDto(c.getId(),c.getData())).collect(Collectors.toList());
        return new BookDto(book.getId(),book.getName(),new AuthorDto(book.getAuthor().getId(),book.getAuthor().getName()),new GenreDto(book.getGenre().getId(),book.getGenre().getName()),commentDtos);
    }

    private Book toDomain(BookDto bookDto){
        List<Comment> comments=bookDto.getComments().stream().map(c -> new Comment(c.getData())).collect(Collectors.toList());
        return new Book(bookDto.getName(),new Genre(bookDto.getGenre().getId(),bookDto.getGenre().getName()), new Author(bookDto.getAuthor().getId(),bookDto.getAuthor().getName()), comments);
    }


}
