package ru.otus.spring.spring04.service;

import lombok.Data;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring04.config.AppConfig;
import ru.otus.spring.spring04.dto.AnswerDto;
import ru.otus.spring.spring04.enums.ExamResult;
import ru.otus.spring.spring04.model.Question;
import ru.otus.spring.spring04.model.User;
import ru.otus.spring.spring04.model.UserAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Data
public class ExamServiceImpl implements ExamService {

    private ExamDataService examDataService;
    private AppConfig appConfig;
    private CSVResourceService csvResourceService;
    private User user;
    private MessageSource messageSource;


    private List<Question> questionList;

    ExamServiceImpl(ExamDataService examDataService, AppConfig appConfig, CSVResourceService csvResourceService,MessageSource messageSource) {
        this.examDataService = examDataService;
        this.appConfig = appConfig;
        this.csvResourceService = csvResourceService;
        this.messageSource=messageSource;
    }

    @Override
    public void setUser(String username){
        user=new User(username,new ArrayList<>());
    }
    @Override
    public String getUserName(){
        if (user==null){
            return getMessage("not-begin");
        }
        return user.getName();
    }
    @Override
    public void loadQuestion(){
        questionList = examDataService.getAllQuestions(
                csvResourceService.getLineListByResourceStream(true, csvResourceService.getCSVResourceStream()));
    }
    @Override
    public String getNextQuestion(){
        if (user.getUserAnswers().size()>(questionList.size()-1)){
            return getMessage("end-exam");
        }
        return questionList.get(user.getUserAnswers().size()).getName();
    }
    @Override
    public List<String> getAnswerOptions(){
        if (user.getUserAnswers().size()>(questionList.size()-1)){
            return Arrays.asList(getMessage("end-exam"));
        }
        return questionList.get(user.getUserAnswers().size()).getAnswerList().stream().map(item -> String.valueOf(item.getNumber())+" - "+item.getName()).collect(Collectors.toList());
    }
    @Override
    public void setAnswerOption(int numberAnswer){
        if (user.getUserAnswers().size()>(questionList.size()-1)){
            return;
        }
        Question question=questionList.get(user.getUserAnswers().size());
        AnswerDto answerDto=new AnswerDto(question,numberAnswer);
        user=userAnswer(user,answerDto);
    }

    @Override
    public String getResult(){
        if (user==null){
            return getMessage("not-begin");
        }
        List<UserAnswer> userAnswers = user.getUserAnswers();
        ExamResult examResult = examDataService.getResultExam(userAnswers, appConfig.getMinCorrectAnswers());
        return examResult.name();
    }

    public User userAnswer(User user, AnswerDto answerDto) {
        return examDataService.addUserAnswer(user,answerDto);
    }

    private String getMessage(String code){
        return messageSource.getMessage(code,null, getLocale());
    }

    private Locale getLocale(){
        return Locale.forLanguageTag(appConfig.getLocaleName());
    }

}
