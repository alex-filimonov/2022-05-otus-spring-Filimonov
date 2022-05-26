package ru.otus.spring.spring01.dao;

import ru.otus.spring.spring01.model.Answer;

public interface AnswerDao {
    Answer create(String name, Boolean condition);
}
