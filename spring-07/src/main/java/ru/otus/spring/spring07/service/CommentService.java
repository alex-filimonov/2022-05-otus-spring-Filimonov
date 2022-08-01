package ru.otus.spring.spring07.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring07.models.Book;
import ru.otus.spring.spring07.models.Comment;
import ru.otus.spring.spring07.repository.AuthorRepository;
import ru.otus.spring.spring07.repository.BookRepository;
import ru.otus.spring.spring07.repository.CommentRepository;
import ru.otus.spring.spring07.repository.GenreRepository;

import javax.transaction.Transactional;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private CommentRepository commentRepository;

    CommentService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }

    public Comment findById(int id){
        return commentRepository.findById(id).get();
    }

    @Transactional
    public Comment add(int bookId, String data){
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){ return null;};
        Comment comment=new Comment();
        comment.setBook(book.get());
        comment.setData(data);
        return commentRepository.save(comment);
    }

    @Transactional
    public void update(int id, int bookId, String data){
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){ return; };
        Optional<Comment> comment=commentRepository.findById(id);
        if (comment.isPresent()){
            comment.get().setBook(book.get());
            comment.get().setData(data);
            commentRepository.save(comment.get());
        }
    }

    @Transactional
    public void delete(int id){
        commentRepository.deleteById(id);
    }

}
