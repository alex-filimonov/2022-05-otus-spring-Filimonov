package ru.otus.spring.spring06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.spring06.models.Author;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Genre;
import ru.otus.spring.spring06.repository.AuthorRepositoryJpa;
import ru.otus.spring.spring06.repository.BookRepositoryJpa;

import java.util.List;

@SpringBootApplication
public class Spring06Application {

    public static void main(String[] args) {

        ApplicationContext context=SpringApplication.run(Spring06Application.class, args);
        /*
        BookRepositoryJpa bookRepositoryJpa=context.getBean(BookRepositoryJpa.class);
        AuthorRepositoryJpa authorRepositoryJpa=context.getBean(AuthorRepositoryJpa.class);
        List<Book> bookList=bookRepositoryJpa.findAll();

        System.out.println(bookList.get(0).getName());

        Author author=new Author();
        author.setName("Test");
        authorRepositoryJpa.save(author);
*/

/*
        Book book=bookRepositoryJpa.findById(1).get();
        System.out.println(book.toString());

        Book book2 =new Book();
        book2.setName("Test book");
        bookRepositoryJpa.save(book2);
*/
    }

}
