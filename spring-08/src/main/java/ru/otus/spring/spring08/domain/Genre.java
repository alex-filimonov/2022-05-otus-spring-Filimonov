package ru.otus.spring.spring08.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;


@Document(collection="genre")
@Data
public class Genre {
    @Transient
    public static final String SEQUENCE_NAME = "genres_sequence";

    @Id
    private long id;
    private String name;

    public Genre(String name){
        this.name=name;
    }

}
