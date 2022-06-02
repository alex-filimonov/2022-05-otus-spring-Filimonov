package ru.otus.spring.spring02.dao;

import ru.otus.spring.spring02.model.Question;

public interface QuestionDao {
    Question create(int id, String name);
}
