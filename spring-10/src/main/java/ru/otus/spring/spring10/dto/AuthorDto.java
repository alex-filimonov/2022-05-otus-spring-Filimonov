package ru.otus.spring.spring10.dto;

import lombok.Data;
import ru.otus.spring.spring10.domain.Author;

@Data
public class AuthorDto {
    private int id;
    private String name;

    public AuthorDto(){

    }
    public AuthorDto(Author author){
        this.id=author.getId();
        this.name=author.getName();
    }
}
