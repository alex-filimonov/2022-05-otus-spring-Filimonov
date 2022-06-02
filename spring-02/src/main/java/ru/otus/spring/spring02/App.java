package ru.otus.spring.spring02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.spring02.service.ExamService;
import ru.otus.spring.spring02.service.QuestionService;
import ru.otus.spring.spring02.view.ExamView;

import java.io.IOException;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        ExamService examService = context.getBean(ExamService.class);
        examService.start();
    }
}
