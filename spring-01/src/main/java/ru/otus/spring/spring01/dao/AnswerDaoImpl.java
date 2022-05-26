package ru.otus.spring.spring01.dao;

import ru.otus.spring.spring01.model.Answer;

public class AnswerDaoImpl implements AnswerDao {
    public Answer create(String name, Boolean condition) {
        return new Answer(name, condition);
    }
}
