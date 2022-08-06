package ru.otus.spring.spring08.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.spring08.domain.Author;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Genre;
import ru.otus.spring.spring08.repository.AuthorRepository;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.CommentRepository;
import ru.otus.spring.spring08.repository.GenreRepository;
import ru.otus.spring.spring08.shell.LibraryShell;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private GenreRepository genreRepository;

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    public BookService(GenreRepository genreRepository,AuthorRepository authorRepository,BookRepository bookRepository,CommentRepository commentRepository, SequenceGeneratorService sequenceGeneratorService){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book findById(int id){
        return bookRepository.findById(id).get();
    }

    public Book findOneByCommentId(int id){
        return bookRepository.findBookByCommentListIn(id).get();
    }

    public Book add(String bookName,int authorId, int genreId){
        Genre genre=genreRepository.findById(genreId).get();
        Author author=authorRepository.findById(authorId).get();
        Book book=new Book(bookName,genre,author,Collections.emptyList());
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        return bookRepository.save(book);
    }

    public Book update(int bookId, String bookName,int authorId, int genreId){
        Genre genre=genreRepository.findById(genreId).get();
        Author author=authorRepository.findById(authorId).get();
        Book book=bookRepository.findById(bookId).get();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book);
    }

    public Book update(Book book){
        return bookRepository.save(book);
    }

    public void delete(int bookId){
        bookRepository.deleteById(bookId);
    }


}
