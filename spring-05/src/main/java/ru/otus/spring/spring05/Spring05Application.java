package ru.otus.spring.spring05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.spring05.dao.BookDao;
import ru.otus.spring.spring05.domain.Author;
import ru.otus.spring.spring05.domain.Book;
import ru.otus.spring.spring05.domain.Genre;

import java.util.List;

@SpringBootApplication
public class Spring05Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Spring05Application.class);
/*
        BookDao bookDao= context.getBean(BookDao.class);
        System.out.println("All count " + bookDao.count());
        Book book=bookDao.getById(0);
        System.out.println("books " + book.toString());

        Book newBook=new Book(0,"Test",new Author(0,"Agatha Christie"),new Genre(1,"detective"));
        newBook=bookDao.add(newBook);

        List<Book> bookList=bookDao.getAll();
        System.out.println("books " + bookList.toString());

        newBook.setName("re Test");
        bookDao.update(newBook);

        bookList=bookDao.getAll();
        System.out.println("books " + bookList.toString());
*/
    }

}
