package ru.otus.spring.spring04.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private int number;
    private String name;
    private Boolean condition;
}
