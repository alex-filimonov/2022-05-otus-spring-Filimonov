package ru.otus.spring.spring07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.spring07.domain.Author;
import ru.otus.spring.spring07.domain.Book;
import ru.otus.spring.spring07.domain.Genre;
import ru.otus.spring.spring07.repository.AuthorRepository;
import ru.otus.spring.spring07.repository.BookRepository;
import ru.otus.spring.spring07.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий book")
@DataJpaTest
public class BookTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @DisplayName("get all book")
    @Test
    void findAllTest(){
        List<Book> books=bookRepository.findAll();
        assertThat(books.size() > 0);
    }

    @DisplayName("add book")
    @Test
    void addBookTest(){
        Genre genre=genreRepository.findById(0).get();
        Author author=authorRepository.findById(0).get();
        Book book=new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setName("test2");
        bookRepository.save(book);
        List<Book> books=bookRepository.findAll();
        assertThat(books.size() > 1);

    }

    @DisplayName("update book")
    @Test
    void updateBookTest(){
        Book book=bookRepository.findById(1).get();
        book.setName("test3");
        bookRepository.save(book);
        Book book1=bookRepository.findById(1).get();
        assertThat(book1.getName().equals("test3"));
    }

    @DisplayName("delete book")
    @Test
    void deleteBookTest(){
        Genre genre=genreRepository.findById(0).get();
        Author author=authorRepository.findById(0).get();
        Book book=new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setName("test3");
        bookRepository.save(book);
        List<Book> books=bookRepository.findAll();
        int countBeforeDelete=books.size();
        bookRepository.deleteById(book.getId());
        List<Book> books2=bookRepository.findAll();

        assertThat(books2.size()<countBeforeDelete);

    }


}
