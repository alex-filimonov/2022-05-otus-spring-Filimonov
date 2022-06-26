package ru.otus.spring.spring04.service;

import ru.otus.spring.spring04.dto.AnswerDto;
import ru.otus.spring.spring04.enums.ExamResult;
import ru.otus.spring.spring04.model.Question;
import ru.otus.spring.spring04.model.User;
import ru.otus.spring.spring04.model.UserAnswer;

import java.util.List;

public interface ExamDataService {

    User addUserAnswer(User user,AnswerDto answerDto);

    ExamResult getResultExam(List<UserAnswer> userAnswers, int minCorrectAnswerCount);

    List<Question> getAllQuestions(List<String> lines);

}
