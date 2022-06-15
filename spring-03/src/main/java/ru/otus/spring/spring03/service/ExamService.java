package ru.otus.spring.spring03.service;

import ru.otus.spring.spring03.dto.AnswerDto;
import ru.otus.spring.spring03.dto.UserDto;
import ru.otus.spring.spring03.model.User;

public interface ExamService {
    void start();

    User userAnswer(User user, AnswerDto answerDto);
}
