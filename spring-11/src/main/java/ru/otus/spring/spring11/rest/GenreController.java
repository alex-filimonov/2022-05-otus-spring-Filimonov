package ru.otus.spring.spring11.rest;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.AuthorRepository;
import ru.otus.spring.spring11.repository.GenreRepository;

@RestController
@Log4j2
public class GenreController {

    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    @GetMapping("/api/genres")
    public Flux<Genre> all(){
        return genreRepository.findAll();
    }


}
