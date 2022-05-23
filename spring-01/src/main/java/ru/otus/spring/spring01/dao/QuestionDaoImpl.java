package ru.otus.spring.spring01.dao;

import ru.otus.spring.spring01.model.Question;

public class QuestionDaoImpl implements QuestionDao {

    public Question create(String[] fields) {
        return new Question(Integer.parseInt(fields[0]), fields[1]);
    }

}
