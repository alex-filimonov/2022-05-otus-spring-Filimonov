package ru.otus.spring.spring08.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Comment;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.CommentRepository;

@Service
@Log4j2
public class CommentService {

    private CommentRepository commentRepository;
    private SequenceGeneratorService sequenceGeneratorService;
    private BookService bookService;

    public CommentService(CommentRepository commentRepository, SequenceGeneratorService sequenceGeneratorService, BookService bookService){
        this.commentRepository=commentRepository;
        this.bookService=bookService;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    public Comment add(int bookId, String data){
        Comment comment=new Comment(data);
        comment.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        Comment resultComment=commentRepository.save(comment);
        Book book=bookService.findById(bookId);
        book.getCommentList().add(comment);
        bookService.update(book);
        return  resultComment;
    }

    public Comment update(int id, String data){
        Comment comment=commentRepository.findById(id).get();
        comment.setData(data);
        return commentRepository.save(comment);
    }

    public void delete(int id){
        Book book=bookService.findOneByCommentId(id);
        Comment comment=commentRepository.findById(id).get();
        book.getCommentList().removeIf(b->(b.getId()==id));
        bookService.update(book);
        commentRepository.delete(comment);
    }


}
