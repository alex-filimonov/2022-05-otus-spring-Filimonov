package ru.otus.spring.spring02.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.UserAnswer;

@Service
public class UserAnswerDaoImpl implements UserAnswerDao{
    public UserAnswer create(Question question, Answer answer, int userAnswerNumber){
        return new UserAnswer(question,userAnswerNumber,answer);
    }
}
