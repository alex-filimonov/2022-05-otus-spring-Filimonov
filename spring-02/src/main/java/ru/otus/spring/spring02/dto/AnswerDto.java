package ru.otus.spring.spring02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;

@Data
@AllArgsConstructor
public class AnswerDto {
    private Question question;
    private int userAnswerNumber;
}
