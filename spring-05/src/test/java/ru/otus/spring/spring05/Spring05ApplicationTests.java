package ru.otus.spring.spring05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.spring05.dao.BookDaoJdbc;
import ru.otus.spring.spring05.domain.Author;
import ru.otus.spring.spring05.domain.Book;
import ru.otus.spring.spring05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao for book")
@JdbcTest
@Import(BookDaoJdbc.class)
class Spring05ApplicationTests {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("get all book test")
    @Test
    void getAllBookTest() {
        List<Book> bookList = bookDaoJdbc.getAll();
        assertThat(bookList.size() > 0);
    }

    @DisplayName("add book test")
    @Test
    void addBookTest() {
        Book book = new Book(0, "test2", new Author(0, ""), new Genre(0, ""));
        bookDaoJdbc.add(book);
        List<Book> bookList = bookDaoJdbc.getAll();
        assertThat(bookList.size() > 1);
    }

    @DisplayName("update book test")
    @Test
    void updateBookTest() {
        Book book = bookDaoJdbc.getById(0);
        book.setName("test3");
        bookDaoJdbc.update(book);
        Book updatedBook = bookDaoJdbc.getById(0);
        assertThat(updatedBook.getName().equals("test3"));
    }

    @DisplayName("delete book test")
    @Test
    void deleteBookTest() {
        Book book = new Book(0, "test2", new Author(0, ""), new Genre(0, ""));
        bookDaoJdbc.add(book);
        int oldCount = bookDaoJdbc.count();
        bookDaoJdbc.delete(book);
        int newCount = bookDaoJdbc.count();
        assertThat(oldCount > newCount);

    }

}
