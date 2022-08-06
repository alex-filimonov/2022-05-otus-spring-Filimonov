package ru.otus.spring.spring08;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Comment;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.CommentRepository;
import ru.otus.spring.spring08.service.SequenceGeneratorService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Репозиторий comment на основе mongoDB")
@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;

    @DisplayName("get all comment from book id=1")
    @Test
    public void findAllCommentTest() {
        Book book = bookRepository.findById(1).get();
        assertThat(book.getCommentList().size() > 0);
    }

    @DisplayName("add comment")
    @Test
    public void addCommentTest() {
        Book book = bookRepository.findById(1).get();
        Comment comment = new Comment("data12");
        comment.setId(15);
        Comment resultComment = commentRepository.save(comment);
        book.getCommentList().add(resultComment);
        bookRepository.save(book);
        Book bookAddComment = bookRepository.findById(1).get();
        assertThat(bookAddComment.getCommentList().size() > 2);
    }

    @DisplayName("update comment")
    @Test
    public void updateCommentTest() {
        Comment comment = commentRepository.findById(2).get();
        comment.setData("test4");
        commentRepository.save(comment);
        Comment comment2 = commentRepository.findById(2).get();
        assertThat(comment2.getData().equals("test4"));
    }

    @DisplayName("delete comment")
    @Test
    public void deleteCommentTest() {
        Book book = bookRepository.findById(1).get();
        Comment commentNew = new Comment("data12");
        commentNew.setId(16);
        Comment resultComment = commentRepository.save(commentNew);
        book.getCommentList().add(resultComment);
        bookRepository.save(book);

        int commentCountBeforeDelete=book.getCommentList().size();
        Comment comment=commentRepository.findById(16).get();
        book.getCommentList().removeIf(c->(c.getId()==16));
        commentRepository.delete(comment);
        bookRepository.save(book);
        Book bookAfter = bookRepository.findById(1).get();
        assertThat(bookAfter.getCommentList().size()<commentCountBeforeDelete);
    }

}
