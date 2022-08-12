package ru.otus.spring.spring10.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring10.domain.Book;
import ru.otus.spring.spring10.domain.Comment;
import ru.otus.spring.spring10.dto.CommentDto;
import ru.otus.spring.spring10.repository.AuthorRepository;
import ru.otus.spring.spring10.repository.BookRepository;
import ru.otus.spring.spring10.repository.CommentRepository;
import ru.otus.spring.spring10.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {


    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private CommentRepository commentRepository;

    CommentServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }
    @Override
    public Comment findById(int id){
        return commentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Comment add(CommentDto commentDto){
        Optional<Book> book = bookRepository.findById(commentDto.getBookId());
        if (!book.isPresent()){ return null;};
        Comment comment=new Comment();
        comment.setBook(book.get());
        comment.setData(commentDto.getData());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void update(CommentDto commentDto){
        Optional<Book> book = bookRepository.findById(commentDto.getBookId());
        if (!book.isPresent()){ return; };
        Optional<Comment> comment=commentRepository.findById(commentDto.getId());
        if (comment.isPresent()){
            comment.get().setBook(book.get());
            comment.get().setData(commentDto.getData());
            commentRepository.save(comment.get());
        }
    }
    @Override
    @Transactional
    public void delete(int id){
        commentRepository.deleteById(id);
    }
}
