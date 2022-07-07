package ru.otus.spring.spring04.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private int id;
    private String name;
    private List<Answer> answerList;

    public Question(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
