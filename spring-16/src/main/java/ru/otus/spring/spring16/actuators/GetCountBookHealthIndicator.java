package ru.otus.spring.spring16.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.spring16.repository.BookRepository;

@Component
public class GetCountBookHealthIndicator implements HealthIndicator {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Health health() {
        try{
            return Health.up().withDetail("count",bookRepository.count()).build();

        }catch (Exception ex){
            return Health.down().withDetail("error",ex.getMessage()).build();
        }
    }
}
