package ru.otus.spring.spring07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Spring07Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring07Application.class, args);

    }

}
