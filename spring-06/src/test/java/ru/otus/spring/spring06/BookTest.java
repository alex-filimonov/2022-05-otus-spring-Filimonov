package ru.otus.spring.spring06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.spring06.models.Author;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Genre;
import ru.otus.spring.spring06.repository.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Репозиторий book на основе jpa")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class})
class BookTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @DisplayName("get book by id")
    @Test
    void findAllTest(){
        List<Book> books=bookRepositoryJpa.findAll();
        assertThat(books.size() > 0);
    }

    @DisplayName("add book")
    @Test
    void addBookTest(){
        Genre genre=genreRepositoryJpa.findById(0).get();
        Author author=authorRepositoryJpa.findById(0).get();
        Book book=new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setName("test2");
        bookRepositoryJpa.save(book);
        List<Book> books=bookRepositoryJpa.findAll();
        assertThat(books.size() > 1);

    }

    @DisplayName("update book")
    @Test
    void updateBookTest(){
        Book book=bookRepositoryJpa.findById(1).get();
        book.setName("test3");
        bookRepositoryJpa.save(book);
        Book book1=bookRepositoryJpa.findById(1).get();
        assertThat(book1.getName().equals("test3"));
    }

    @DisplayName("delete book")
    @Test
    void deleteBookTest(){
        Genre genre=genreRepositoryJpa.findById(0).get();
        Author author=authorRepositoryJpa.findById(0).get();
        Book book=new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setName("test3");
        bookRepositoryJpa.save(book);
        List<Book> books=bookRepositoryJpa.findAll();
        int countBeforeDelete=books.size();
        bookRepositoryJpa.deleteById(book.getId());
        List<Book> books2=bookRepositoryJpa.findAll();

        assertThat(books2.size()<countBeforeDelete);

    }

}
