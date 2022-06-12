package ru.otus.spring.spring02.service;

import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;

import java.util.List;

public interface ExamDataService {

    User addUserAnswer(User user,AnswerDto answerDto);

    ExamResult getResultExam(List<UserAnswer> userAnswers, int minCorrectAnswerCount);

    List<Question> getAllQuestions(List<String> lines);

}
