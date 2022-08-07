package ru.otus.spring.spring09.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.spring09.domain.Author;
import ru.otus.spring.spring09.domain.Book;
import ru.otus.spring.spring09.domain.Genre;
import ru.otus.spring.spring09.dto.BookDto;
import ru.otus.spring.spring09.service.*;

import java.util.List;

@Controller
public class BookController {
    private BookService bookService;
    private AuthorService authorService;

    private GenreService genreService;

    public BookController(BookService bookService, AuthorServiceImpl authorService, GenreService genreService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPage(Model model){
        List<Book> bookList= bookService.getAll();
        model.addAttribute("bookList", bookList);
        return "listBook";
    }

    @GetMapping("/addBook")
    public String addBookPage(Model model){
        List<Author> authorList= authorService.getAll();
        model.addAttribute("authorList",authorList);
        List<Genre> genreList= genreService.getAll();
        model.addAttribute("genreList",genreList);
        model.addAttribute("book", new BookDto());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(BookDto book){
        bookService.add(book);
        return "redirect:/";
    }

    @GetMapping("/editBook")
    public String editBookPage(Model model){
        List<Author> authorList= authorService.getAll();
        model.addAttribute("authorList",authorList);
        List<Genre> genreList= genreService.getAll();
        model.addAttribute("genreList",genreList);
        model.addAttribute("book", new BookDto());
        return "editBook";
    }


}
