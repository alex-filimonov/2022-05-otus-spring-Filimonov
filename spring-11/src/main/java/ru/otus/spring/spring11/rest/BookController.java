package ru.otus.spring.spring11.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.rest.dto.BookDto;
import ru.otus.spring.spring11.service.BookService;
import ru.otus.spring.spring11.service.BookServiceImpl;

@RestController
@Log4j2
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public Flux<BookDto> all(){
        return bookService.all();
    }

    @GetMapping("/api/book/{id}")
    public Mono<BookDto> get(@PathVariable String id){
        return bookService.get(id);
    }

    @PostMapping("/api/book")
    public Mono<BookDto> add(@RequestBody Mono<BookDto> bookDtoMono) {
        return bookService.add(bookDtoMono);
    }

    @PutMapping("/api/book/{id}")
    public Mono<BookDto> update(@PathVariable String  id, @RequestBody BookDto bookDto) {
        return bookService.update(id,bookDto);
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return bookService.delete(id);
    }



}
