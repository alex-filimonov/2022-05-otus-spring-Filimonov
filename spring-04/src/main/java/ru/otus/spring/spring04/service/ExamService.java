package ru.otus.spring.spring04.service;

import ru.otus.spring.spring04.dto.AnswerDto;
import ru.otus.spring.spring04.model.User;

import java.util.List;

public interface ExamService {
    User userAnswer(User user, AnswerDto answerDto);
    void setUser(String username);
    String getUserName();
    void loadQuestion();
    String getNextQuestion();
    List<String> getAnswerOptions();
    void setAnswerOption(int numberAnswer);
    String getResult();

}
