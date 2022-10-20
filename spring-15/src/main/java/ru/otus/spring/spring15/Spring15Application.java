package ru.otus.spring.spring15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring15Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring15Application.class);
    }
}
