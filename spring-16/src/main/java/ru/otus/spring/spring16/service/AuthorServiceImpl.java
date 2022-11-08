package ru.otus.spring.spring16.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring16.domain.Author;
import ru.otus.spring.spring16.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    @Override
    public List<Author> getAll(){
        return (List<Author>) authorRepository.findAll();
    }

}