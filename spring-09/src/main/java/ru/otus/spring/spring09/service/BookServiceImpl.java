package ru.otus.spring.spring09.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring09.domain.Author;
import ru.otus.spring.spring09.domain.Book;
import ru.otus.spring.spring09.domain.Genre;
import ru.otus.spring.spring09.dto.BookDto;
import ru.otus.spring.spring09.repository.AuthorRepository;
import ru.otus.spring.spring09.repository.BookRepository;
import ru.otus.spring.spring09.repository.CommentRepository;
import ru.otus.spring.spring09.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private CommentRepository commentRepository;

    BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id){
        return bookRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Book add(BookDto bookDto){
        Author author=authorRepository.findById(bookDto.getAuthor().getId()).get();
        Genre genre=genreRepository.findById(bookDto.getGenre().getId()).get();
        Book book=new Book();
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book);
    }


    @Override
    @Transactional
    public void update(int bookId, String bookName, int authorId, int genreId){
        Author author=authorRepository.findById(authorId).get();
        Genre genre=genreRepository.findById(genreId).get();
        Book book=bookRepository.findById(bookId).get();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(int bookId){
        bookRepository.findById(bookId).ifPresent(b->{
            bookRepository.delete(b);
        });

    }
}
