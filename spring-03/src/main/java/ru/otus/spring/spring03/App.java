package ru.otus.spring.spring03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.spring03.service.ExamService;

import java.io.IOException;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);

    }
}
