package ru.otus.spring.spring03.view;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring03.config.AppConfig;
import ru.otus.spring.spring03.dto.AnswerDto;
import ru.otus.spring.spring03.dto.UserDto;
import ru.otus.spring.spring03.enums.ExamResult;
import ru.otus.spring.spring03.model.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class ExamViewImpl implements ExamView {
    private MessageSource messageSource;
    private AppConfig appConfig;

    ExamViewImpl(MessageSource messageSource, AppConfig appConfig){
        this.messageSource=messageSource;
        this.appConfig=appConfig;
    }

    @Override
    public UserDto userSet() {
        System.out.println(getMessage("form.user-set.enter-name"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return new UserDto(reader.readLine());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void startExamMessage() {
        System.out.println(getMessage("form.start-exam.start-message"));
        System.out.println(getMessage("horizontal_line"));
    }

    @Override
    public AnswerDto userAnswerForm(Question question) {
        System.out.println(getMessage("form.question.question-message"));
        System.out.println(question.getName());
        System.out.println(getMessage("form.question.answer-options-message"));
        question.getAnswerList().forEach(answer -> {
            System.out.printf(getMessage("form.question.answer_options_format"), answer.getNumber(), answer.getName());
        });
        System.out.println(getMessage("form.question.enter-the-number-of-the-correct-answer-message"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            AnswerDto answerDto = new AnswerDto(question, Integer.parseInt(reader.readLine()));
            return answerDto;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new AnswerDto(question, 0);
    }

    @Override
    public void endExamMessage() {
        System.out.println(getMessage("horizontal_line"));
        System.out.println(getMessage("form.finish-exam.finish-message"));
    }

    @Override
    public void resultExamMessage(String userName, ExamResult examResult) {
        System.out.println(getMessage("horizontal_line"));
        System.out.println(getMessage("horizontal_line"));
        System.out.printf(getMessage("form.result.exam_result_for_message"), userName);
        System.out.println(examResult.name());
        System.out.println(getMessage("horizontal_line"));
        System.out.println(getMessage("horizontal_line"));
    }


    private String getMessage(String code){
        return messageSource.getMessage(code,null, getLocale());
    }

    private Locale getLocale(){
        return Locale.forLanguageTag(appConfig.getLocaleName());
    }


}
