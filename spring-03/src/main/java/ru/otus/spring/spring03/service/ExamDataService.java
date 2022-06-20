package ru.otus.spring.spring03.service;

import ru.otus.spring.spring03.dto.AnswerDto;
import ru.otus.spring.spring03.dto.UserDto;
import ru.otus.spring.spring03.enums.ExamResult;
import ru.otus.spring.spring03.model.Question;
import ru.otus.spring.spring03.model.User;
import ru.otus.spring.spring03.model.UserAnswer;

import java.util.List;

public interface ExamDataService {

    User addUserAnswer(User user,AnswerDto answerDto);

    ExamResult getResultExam(List<UserAnswer> userAnswers, int minCorrectAnswerCount);

    List<Question> getAllQuestions(List<String> lines);

}
