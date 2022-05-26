package ru.otus.spring.spring01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String name;
    private Boolean condition;
}
