package ru.otus.spring.spring02.service;

import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;

import java.util.List;

public interface ExamDataService {
    void createUser(UserDto userDto);
    List<Question> getAllQuestions();
    void addUserAnswer(AnswerDto answerDto);
    ExamResult getResultExam(List<UserAnswer> userAnswers, int minCorrectAnswerCount);
    User getUser();
    List<UserAnswer> getUserAnswers();
}
