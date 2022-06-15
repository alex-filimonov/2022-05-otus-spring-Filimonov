package ru.otus.spring.spring03.repository;

import ru.otus.spring.spring03.model.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> getAnswersFromArray(String[] fields);

    Answer getAnswerByNumber(List<Answer> answers, int number);
}
