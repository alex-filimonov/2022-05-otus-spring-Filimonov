package ru.otus.spring.spring11;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.AuthorRepository;
import ru.otus.spring.spring11.repository.BookRepository;
import ru.otus.spring.spring11.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
@Log4j2
public class Spring11Application {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(Spring11Application.class, args);

        AuthorRepository authorRepository=context.getBean(AuthorRepository.class);
        Flux<Author> authorFlux=authorRepository.saveAll(Arrays.asList(new Author(0,"Agatha Christie"), new Author(1,"Robert Anson Heinlein")));
        authorFlux.subscribe();

        GenreRepository genreRepository=context.getBean(GenreRepository.class);
        Flux<Genre> genreFlux=genreRepository.saveAll(Arrays.asList(new Genre(0,"detective"),new Genre(1,"fantastic")));
        genreFlux.subscribe();



    }

}
