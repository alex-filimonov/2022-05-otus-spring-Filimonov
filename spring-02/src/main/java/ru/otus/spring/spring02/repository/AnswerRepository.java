package ru.otus.spring.spring02.repository;

import ru.otus.spring.spring02.model.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> getAnswersFromArray(String[] fields);

    Answer getAnswerByNumber(List<Answer> answers, int number);
}
