package ru.otus.spring.spring12.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring12.domain.Genre;
import ru.otus.spring.spring12.repository.GenreRepository;

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
