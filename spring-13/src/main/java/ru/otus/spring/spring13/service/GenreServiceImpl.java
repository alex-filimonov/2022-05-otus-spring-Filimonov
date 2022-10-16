package ru.otus.spring.spring13.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring13.domain.Genre;
import ru.otus.spring.spring13.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    @Override
    public List<Genre> getAll(){
        return (List<Genre>) genreRepository.findAll();
    }

}
