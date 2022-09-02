package ru.otus.spring.spring11.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.AuthorRepository;
import ru.otus.spring.spring11.repository.BookRepository;
import ru.otus.spring.spring11.repository.GenreRepository;
import ru.otus.spring.spring11.rest.dto.AuthorDto;
import ru.otus.spring.spring11.rest.dto.BookDto;
import ru.otus.spring.spring11.rest.dto.GenreDto;

import java.util.Collections;

@RestController
@Log4j2
public class BookController {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;



    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
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
    public Mono<Book> add(@RequestBody BookDto bookDto) {
        Mono<Author> authorMono=authorRepository.findById(bookDto.getAuthor().getId());
        Mono<Genre> genreMono=genreRepository.findById(bookDto.getGenre().getId());
        Mono<Tuple2<Author,Genre>> tuple2Mono=Mono.zip(authorMono,genreMono);
        Book b=new Book(bookDto.getName(),new Genre(bookDto.getGenre().getId(),bookDto.getGenre().getName()), new Author(bookDto.getAuthor().getId(),bookDto.getAuthor().getName()), Collections.emptyList());

        tuple2Mono.map(t->{
            Author a=t.getT1();
            Genre g=t.getT2();
            b.setAuthor(a);
            b.setGenre(g);
            bookRepository.save(b);
            return b;
        });


/*
        return bookDtoMono.map(bookDto->{
            Book b=new Book(bookDto.getName(),new Genre(bookDto.getGenre().getId(),bookDto.getGenre().getName()), new Author(bookDto.getAuthor().getId(),bookDto.getAuthor().getName()), Collections.emptyList());
            Mono<Author> authorMono=authorRepository.findById(bookDto.getAuthor().getId());
            Mono<Genre> genreMono=genreRepository.findById(bookDto.getGenre().getId());
            Mono<Tuple2<Author,Genre>> tuple2Mono=Mono.zip(authorMono,genreMono);
            tuple2Mono.map(t->{
                Author a=t.getT1();
                Genre g=t.getT2();
                b.setAuthor(a);
                b.setGenre(g);
                bookRepository.save(b);
                return b;
            });
*/
            /*
            Mono<Author> authorMono=authorRepository.findById(bookDto.getAuthor().getId());
            Mono<Genre> genreMono=genreRepository.findById(bookDto.getGenre().getId());
            Mono<Tuple2<Author,Genre>> tuple2Mono=Mono.zip(authorMono,genreMono);
            return tuple2Mono.map(t->{
               Author a=t.getT1();
               Genre g=t.getT2();
               Book b=new Book(bookDto.getName(),g,a,Collections.emptyList());
               bookRepository.save(b);
               return b;
            });*/

        /*
        return bookDto.map(dto -> this.toDomain(dto) ).flatMap(book -> {
            return bookRepository.save(book);
        }).map(book -> this.toDto(book));*/
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


    private BookDto toDto(Book book){
        return new BookDto(book.getId(),book.getName(),new AuthorDto(book.getAuthor().getId(),book.getAuthor().getName()),new GenreDto(book.getGenre().getId(),book.getGenre().getName()));
    }

    private Book toDomain(BookDto bookDto){
        return new Book(bookDto.getName(),new Genre(bookDto.getGenre().getId(),bookDto.getGenre().getName()), new Author(bookDto.getAuthor().getId(),bookDto.getAuthor().getName()), Collections.emptyList());
    }


}
