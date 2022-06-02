package ru.otus.spring.spring02.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.model.Question;

@Service
public class QuestionDaoImpl implements QuestionDao {
    public Question create(int id, String name) {
        return new Question(id,name);
    }

}
