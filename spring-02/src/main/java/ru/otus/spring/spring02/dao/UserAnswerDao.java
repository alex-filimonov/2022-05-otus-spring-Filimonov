package ru.otus.spring.spring02.dao;

import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.UserAnswer;

public interface UserAnswerDao {
    UserAnswer create(Question question, Answer answer, int userAnswerNumber);
}
