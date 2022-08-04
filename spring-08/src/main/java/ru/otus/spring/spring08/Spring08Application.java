package ru.otus.spring.spring08;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Genre;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.GenreRepository;

import java.util.List;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Spring08Application {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private GenreRepository repository;

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(Spring08Application.class, args);
    }

}
