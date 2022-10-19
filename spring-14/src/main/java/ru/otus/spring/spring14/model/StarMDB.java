package ru.otus.spring.spring14.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Star")
@Data
public class StarMDB {
    @Id
    private String id;
    private String name;
    private Float distance;
}
