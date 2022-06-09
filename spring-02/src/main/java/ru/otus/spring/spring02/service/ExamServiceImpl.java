package ru.otus.spring.spring02.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.config.AppConfig;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;
import ru.otus.spring.spring02.view.ExamView;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ExamServiceImpl implements ExamService {

    private ExamView examView;
    private ExamDataService examDataService;
    private AppConfig appConfig;
    private CSVResourceService csvResourceService;

    ExamServiceImpl(ExamView examView, ExamDataService examDataService, AppConfig appConfig, CSVResourceService csvResourceService) {
        this.examView = examView;
        this.examDataService = examDataService;
        this.setExamDataService(this.examDataService);
        this.appConfig = appConfig;
        this.csvResourceService = csvResourceService;
    }

    @Override
    public void start() {
        List<Question> questionList = examDataService.getAllQuestions(
                csvResourceService.getLineListByResourceStream(true, csvResourceService.getCSVResourceStream()));

        User user=new User(examView.userSet().getName(),new ArrayList<>());

        examView.startExamMessage();
        for (Question question : questionList){
            user=userAnswer(user,examView.userAnswerForm(question));
        }
        examView.endExamMessage();

        List<UserAnswer> userAnswers = user.getUserAnswers();
        ExamResult examResult = examDataService.getResultExam(userAnswers, appConfig.getMinCorrectAnswers());
        examView.resultExamMessage(user.getName(), examResult);
    }

    @Override
    public User userAnswer(User user, AnswerDto answerDto) {
        return examDataService.addUserAnswer(user,answerDto);
    }

}
