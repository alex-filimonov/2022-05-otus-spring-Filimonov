package ru.otus.spring.spring04.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAnswer {
    private Question question;
    private int userAnswerNumber;
    private Answer answer;
}
