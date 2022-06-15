package ru.otus.spring.spring03.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class AppConfig {
    @Value("${exam.min_correct_answers}")
    private int minCorrectAnswers;
    @Value("${exam.question_file}")
    private String questionFileName;
}
