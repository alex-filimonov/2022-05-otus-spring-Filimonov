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
import ru.otus.spring.spring06.repository.AuthorRepositoryJpa;
import ru.otus.spring.spring06.repository.BookRepositoryJpa;
import ru.otus.spring.spring06.repository.CommentRepositoryJpa;
import ru.otus.spring.spring06.repository.GenreRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Comment на основе jpa")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class, CommentRepositoryJpa.class})
public class CommentTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @DisplayName("get comments from bookId")
    @Test
    void findAllTest(){
        List<Comment> commentList=commentRepositoryJpa.findByBookId(1);
        assertThat(commentList.size() > 0);
    }

    @DisplayName("add comment")
    @Test
    void addCommentTest(){
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook_id(1);
        commentRepositoryJpa.save(comment);
        List<Comment> commentList=commentRepositoryJpa.findByBookId(1);
        assertThat(commentList.size() > 1);

    }

    @DisplayName("update comment")
    @Test
    void updateCommentTest(){
        List<Comment> commentList=commentRepositoryJpa.findByBookId(1);
        commentList.get(0).setData("data3_5");
        commentRepositoryJpa.save(commentList.get(0));
        Comment comment=commentRepositoryJpa.findById(commentList.get(0).getId()).get();
        assertThat(comment.getData().equals("data3_5"));
    }

    @DisplayName("delete book")
    @Test
    void deleteBookTest(){
        Comment comment=new Comment();
        comment.setData("data 34");
        comment.setBook_id(1);
        commentRepositoryJpa.save(comment);
        List<Comment> commentList=commentRepositoryJpa.findByBookId(1);
        int countCommentBefore=commentList.size();
        commentRepositoryJpa.deleteById(comment.getId());
        List<Comment> commentList2=commentRepositoryJpa.findByBookId(1);
        assertThat(countCommentBefore>commentList2.size());
    }

}

