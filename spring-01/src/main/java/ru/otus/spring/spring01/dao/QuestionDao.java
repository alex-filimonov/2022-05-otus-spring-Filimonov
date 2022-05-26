package ru.otus.spring.spring01.dao;

import ru.otus.spring.spring01.model.Question;

public interface QuestionDao {
    Question create(String[] fields);
}
