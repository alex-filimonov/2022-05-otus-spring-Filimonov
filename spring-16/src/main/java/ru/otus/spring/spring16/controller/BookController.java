package ru.otus.spring.spring16.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.spring16.domain.Author;
import ru.otus.spring.spring16.domain.Book;
import ru.otus.spring.spring16.domain.Genre;
import ru.otus.spring.spring16.dto.BookDto;
import ru.otus.spring.spring16.service.*;

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
        return "book/listBook";
    }

    @GetMapping("/addBook")
    public String addBookPage(Model model){
        List<Author> authorList= authorService.getAll();
        model.addAttribute("authorList",authorList);
        List<Genre> genreList= genreService.getAll();
        model.addAttribute("genreList",genreList);
        model.addAttribute("book", new BookDto());
        return "book/addBook";
    }

    @PostMapping("/addBook")
    public String addBook(BookDto book){
        bookService.add(book);
        return "redirect:/";
    }

    @GetMapping("/editBook")
    public String editBookPage(@RequestParam("id") int id, Model model){
        Book book=bookService.findById(id);
        model.addAttribute("book", new BookDto(book));
        List<Author> authorList= authorService.getAll();
        model.addAttribute("authorList",authorList);
        List<Genre> genreList= genreService.getAll();
        model.addAttribute("genreList",genreList);
        return "book/editBook";
    }

    @PostMapping("/editBook")
    public String editBook(BookDto bookDto){
        bookService.update(bookDto);
        return "redirect:/";
    }

    @GetMapping("/confirmDeleteBook")
    public String confirmDeleteBook(@RequestParam("id") int id,Model model){
        Book book=bookService.findById(id);
        model.addAttribute("book", new BookDto(book));
        return "book/confirmDeleteBook";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(BookDto bookDto){
        bookService.delete(bookDto.getId());
        return "redirect:/";
    }


}
