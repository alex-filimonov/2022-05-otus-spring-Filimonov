package ru.otus.spring.spring02.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.spring.spring02.model.Answer;

public interface AnswerDao {
    Answer create(int number, String name, Boolean condition);
}
