package ru.otus.spring.spring10.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.spring10.dto.BookDto;
import ru.otus.spring.spring10.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAll(){
        return bookRepository.findAll().stream().map(b->(new BookDto(b))).collect(Collectors.toList());
    }


}
