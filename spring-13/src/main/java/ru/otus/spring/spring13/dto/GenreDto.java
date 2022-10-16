package ru.otus.spring.spring13.dto;

import lombok.Data;
import ru.otus.spring.spring13.domain.Genre;

@Data
public class GenreDto {
    private int id;
    private String name;

    public GenreDto(){

    }

    public GenreDto(Genre genre){
        this.id=genre.getId();
        this.name=genre.getName();
    }

}
