package ru.otus.spring.spring10.restController;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.spring10.dto.BookDto;
import ru.otus.spring.spring10.repository.BookRepository;
import ru.otus.spring.spring10.service.BookService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAll(){
        return bookService.getAll().stream().map(b->(new BookDto(b))).collect(Collectors.toList());
    }

    @GetMapping("/api/book/{id}")
    public BookDto get(@PathVariable Long id){
        return new BookDto(bookService.findById(id));
    }


    @PostMapping("/api/book")
    public void create(@RequestBody BookDto bookDto){
        bookService.add(bookDto);
    }

    @PutMapping("/api/book/{id}")
    public void update(@PathVariable Long id,@RequestBody BookDto bookDto){
        bookService.update(bookDto);
    }

    @DeleteMapping("/api/book/{id}")
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }


}
