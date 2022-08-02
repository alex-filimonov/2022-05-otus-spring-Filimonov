package ru.otus.spring.spring07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.spring07.domain.Book;
import ru.otus.spring.spring07.domain.Comment;
import ru.otus.spring.spring07.repository.AuthorRepository;
import ru.otus.spring.spring07.repository.BookRepository;
import ru.otus.spring.spring07.repository.CommentRepository;
import ru.otus.spring.spring07.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий book")
@DataJpaTest

public class CommentTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommentRepository commentRepository;

    @DisplayName("get all comment")
    @Test
    void findAllTest(){
        Book book=bookRepository.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        assertThat(commentList.size() > 0);
    }

    @DisplayName("add comment")
    @Test
    void addCommentTest(){
        Book book=bookRepository.findById(1).get();
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook(book);
        commentRepository.save(comment);
        Book book2=bookRepository.findById(1).get();
        List<Comment> commentList=book2.getCommentList();
        assertThat(commentList.size() > 1);

    }

    @DisplayName("update comment")
    @Test
    void updateCommentTest(){
        Book book=bookRepository.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        commentList.get(0).setData("data3_5");
        commentRepository.save(commentList.get(0));
        Comment comment=commentRepository.findById(commentList.get(0).getId()).get();
        assertThat(comment.getData().equals("data3_5"));
    }

    @DisplayName("delete comment")
    @Test
    void deleteCommentTest(){
        Book book0=bookRepository.findById(1).get();
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook(book0);
        commentRepository.save(comment);
        Book book=bookRepository.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        int countCommentBefore=commentList.size();
        commentRepository.deleteById(comment.getId());
        Book book1=bookRepository.findById(1).get();
        List<Comment> commentList2=book1.getCommentList();
        assertThat(countCommentBefore>commentList2.size());
    }


}
