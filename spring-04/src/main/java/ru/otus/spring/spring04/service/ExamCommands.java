package ru.otus.spring.spring04.service;


import org.springframework.context.annotation.Scope;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.spring04.model.Question;

import java.util.List;


@ShellComponent
@Scope("singleton")
public class ExamCommands {

    private ExamService examService;

    ExamCommands(ExamServiceImpl examService){
        this.examService=examService;
    }


    @ShellMethod("begin")
    public void begin(String userName){
        examService.setUser(userName);
        examService.loadQuestion();
    }

    @ShellMethod("show-user")
    public String showUser(){
        return examService.getUserName();
    }


    @ShellMethod("show-question")
    public String showQuestion(){
        return examService.getNextQuestion();
    }

    @ShellMethod("show-answer-options")
    public String showAnswerOption(){
        return examService.getAnswerOptions().toString();
    }

    @ShellMethod("set-answer")
    public void setAnswer(int numAnswer){
        examService.setAnswerOption(numAnswer);
    }

    @ShellMethod("show-result")
    public String showResult(){
        return examService.getResult();
    }

}
