package ru.otus.spring.spring02.service;

import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.model.User;

public interface ExamService {
    void start();

    User userAnswer(User user, AnswerDto answerDto);
}
