package ru.otus.spring.spring02.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExamDataServiceImpl implements ExamDataService {
    private QuestionService questionService;

    public ExamDataServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public User addUserAnswer(User user,AnswerDto answerDto) {
        Answer answer = questionService.getAnswerFromQuestionAndNumberAnswer(answerDto.getQuestion(), answerDto.getUserAnswerNumber());
        user.getUserAnswers().add(new UserAnswer(answerDto.getQuestion(),answerDto.getUserAnswerNumber(),answer));
        return user;
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
    public List<Question> getAllQuestions(List<String> lines) {
        return questionService.getAllQuestions(lines);
    }

}
