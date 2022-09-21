package ru.otus.spring.spring11.rest.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private int id;
    private String name;

    public AuthorDto(int id, String name){
        this.id=id;
        this.name=name;
    }

}
