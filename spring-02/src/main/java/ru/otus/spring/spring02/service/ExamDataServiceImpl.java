package ru.otus.spring.spring02.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dao.UserAnswerDao;
import ru.otus.spring.spring02.dao.UserDao;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExamDataServiceImpl implements ExamDataService {
    private User user;
    private QuestionService questionService;
    private UserDao userDao;
    private UserAnswerDao userAnswerDao;

    public ExamDataServiceImpl(QuestionService questionService, UserDao userDao, UserAnswerDao userAnswerDao) {
        this.questionService = questionService;
        this.userDao = userDao;
        this.userAnswerDao = userAnswerDao;
    }

    @Override
    public void createUser(UserDto userDto) {
        user = userDao.create(userDto.getName());
    }

    @Override
    public void addUserAnswer(AnswerDto answerDto) {
        Answer answer = questionService.getAnswerFromQuestionAndNumberAnswer(answerDto.getQuestion(), answerDto.getUserAnswerNumber());
        UserAnswer userAnswer = userAnswerDao.create(answerDto.getQuestion(), answer, answerDto.getUserAnswerNumber());
        user.getUserAnswers().add(userAnswer);
    }

    @Override
    public ExamResult getResultExam(List<UserAnswer> userAnswers, int minCorrectAnswerCount) {
        AtomicInteger rightAnswer = new AtomicInteger();
        try {
            userAnswers.forEach(answer -> {
                if (answer.getAnswer().getCondition().equals(true)) {
                    rightAnswer.getAndIncrement();
                }
            });
            if (rightAnswer.get() >= minCorrectAnswerCount) {
                return ExamResult.PASSED;
            } else {
                return ExamResult.NOT_PASS;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ExamResult.UNKNOWN;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public List<UserAnswer> getUserAnswers() {
        return this.user.getUserAnswers();
    }

    @Override
    public List<Question> getAllQuestions(List<String> lines) {
        return questionService.getAllQuestions(lines);
    }

}
