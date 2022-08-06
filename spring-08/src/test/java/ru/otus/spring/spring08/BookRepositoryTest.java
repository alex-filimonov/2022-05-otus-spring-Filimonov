package ru.otus.spring.spring08;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.spring08.domain.Author;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Genre;
import ru.otus.spring.spring08.repository.AuthorRepository;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.CommentRepository;
import ru.otus.spring.spring08.repository.GenreRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Репозиторий book на основе mongoDB")
@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CommentRepository commentRepository;
    @DisplayName("get all book")
    @Test
    public void findAllTest(){
        List<Book> books=bookRepository.findAll();
        assertThat(books.size() > 0);
    }
    @DisplayName("add book")
    @Test
    public void addBookTest(){
        Genre genre=genreRepository.findById(1).get();
        Author author=authorRepository.findById(1).get();
        Book book=new Book("test book",genre,author, Collections.emptyList());
        bookRepository.save(book);
        List<Book> books=bookRepository.findAll();
        assertThat(books.size() > 1);

    }

    @DisplayName("update book")
    @Test
    public void updateBookTest(){
        Book book=bookRepository.findById(1).get();
        book.setName("test3");
        bookRepository.save(book);
        Book book1=bookRepository.findById(1).get();
        assertThat(book1.getName().equals("test3"));
    }

    @DisplayName("delete book")
    @Test
    public void deleteBookTest(){
        Genre genre=genreRepository.findById(1).get();
        Author author=authorRepository.findById(1).get();
        Book book=new Book("test3",genre,author,Collections.emptyList());
        bookRepository.save(book);
        List<Book> books=bookRepository.findAll();
        int countBeforeDelete=books.size();
        bookRepository.deleteById(book.getId());
        List<Book> books2=bookRepository.findAll();

        assertThat(books2.size()<countBeforeDelete);

    }
}
