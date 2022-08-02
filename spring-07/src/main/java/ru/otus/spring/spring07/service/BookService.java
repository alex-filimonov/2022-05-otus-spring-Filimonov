package ru.otus.spring.spring07.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring07.domain.Author;
import ru.otus.spring.spring07.domain.Book;
import ru.otus.spring.spring07.domain.Genre;
import ru.otus.spring.spring07.repository.AuthorRepository;
import ru.otus.spring.spring07.repository.BookRepository;
import ru.otus.spring.spring07.repository.CommentRepository;
import ru.otus.spring.spring07.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private CommentRepository commentRepository;

    BookService(BookRepository bookRepository,GenreRepository genreRepository,AuthorRepository authorRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book findById(int id){
        return bookRepository.findById(id).get();
    }

    @Transactional
    public Book add(String bookName,int authorId, int genreId){
        Author author=authorRepository.findById(authorId).get();
        Genre genre=genreRepository.findById(genreId).get();
        Book book=new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book);
    }

    @Transactional
    public void update(int bookId, String bookName,int authorId, int genreId){
        Author author=authorRepository.findById(authorId).get();
        Genre genre=genreRepository.findById(genreId).get();
        Book book=bookRepository.findById(bookId).get();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int bookId){
        bookRepository.findById(bookId).ifPresent(b->{
            bookRepository.delete(b);
        });

    }
}
