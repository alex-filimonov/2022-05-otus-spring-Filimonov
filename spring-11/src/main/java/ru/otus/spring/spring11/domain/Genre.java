package ru.otus.spring.spring11.domain;

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
    private int id;
    private String name;

    public Genre(int id,String name){
        this.id=id;
        this.name=name;
    }

}
