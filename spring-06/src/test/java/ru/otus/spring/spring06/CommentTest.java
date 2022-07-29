package ru.otus.spring.spring06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.spring06.models.Author;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Comment;
import ru.otus.spring.spring06.models.Genre;
import ru.otus.spring.spring06.repository.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Comment на основе jpa")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class, CommentRepositoryJpa.class})
public class CommentTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @DisplayName("get comments from bookId")
    @Test
    void findAllTest(){
        Book book=bookRepositoryJpa.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        assertThat(commentList.size() > 0);
    }

    @DisplayName("add comment")
    @Test
    void addCommentTest(){
        Book book=bookRepositoryJpa.findById(1).get();
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook(book);
        commentRepositoryJpa.save(comment);
        Book book2=bookRepositoryJpa.findById(1).get();
        List<Comment> commentList=book2.getCommentList();

        assertThat(commentList.size() > 1);

    }
    @DisplayName("update comment")
    @Test
    void updateCommentTest(){
        Book book=bookRepositoryJpa.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        commentList.get(0).setData("data3_5");
        commentRepositoryJpa.save(commentList.get(0));
        Comment comment=commentRepositoryJpa.findById(commentList.get(0).getId()).get();
        assertThat(comment.getData().equals("data3_5"));
    }

    @DisplayName("delete book")
    @Test
    void deleteBookTest(){
        Book book0=bookRepositoryJpa.findById(1).get();
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook(book0);
        commentRepositoryJpa.save(comment);
        Book book=bookRepositoryJpa.findById(1).get();
        List<Comment> commentList=book.getCommentList();
        int countCommentBefore=commentList.size();
        commentRepositoryJpa.deleteById(comment.getId());
        Book book1=bookRepositoryJpa.findById(1).get();
        List<Comment> commentList2=book1.getCommentList();
        assertThat(countCommentBefore>commentList2.size());
    }

}

