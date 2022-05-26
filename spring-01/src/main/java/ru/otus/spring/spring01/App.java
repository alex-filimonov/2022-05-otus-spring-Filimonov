package ru.otus.spring.spring01;

import lombok.extern.java.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.spring01.service.QuestionService;

import java.io.IOException;

@Log
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = (QuestionService) context.getBean("questionService");
        questionService.outputQuestions();
        context.close();
    }
}
