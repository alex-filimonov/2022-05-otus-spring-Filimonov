package ru.otus.spring.spring10.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.spring10.dto.AuthorDto;
import ru.otus.spring.spring10.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAll(){
        return authorRepository.findAll().stream().map(a->(new AuthorDto(a))).collect(Collectors.toList());
    }

}
