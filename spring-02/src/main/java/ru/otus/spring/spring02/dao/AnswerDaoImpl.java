package ru.otus.spring.spring02.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.model.Answer;

@Service
public class AnswerDaoImpl implements AnswerDao {
    public Answer create(int number,String name, Boolean condition) {
        return new Answer(number, name, condition);
    }
}
