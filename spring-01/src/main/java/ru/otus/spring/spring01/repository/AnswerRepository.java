package ru.otus.spring.spring01.repository;

import ru.otus.spring.spring01.model.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> getAnswersFromArray(String[] fields);
}
