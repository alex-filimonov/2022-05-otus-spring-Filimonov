package ru.otus.spring.spring10.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.spring10.dto.AuthorDto;
import ru.otus.spring.spring10.dto.GenreDto;
import ru.otus.spring.spring10.repository.AuthorRepository;
import ru.otus.spring.spring10.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class GenreController {
    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    @GetMapping("/api/genres")
    public List<GenreDto> getAll(){
        return genreRepository.findAll().stream().map(g->(new GenreDto(g))).collect(Collectors.toList());
    }

}
