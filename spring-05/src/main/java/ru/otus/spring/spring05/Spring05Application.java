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
    }

}
