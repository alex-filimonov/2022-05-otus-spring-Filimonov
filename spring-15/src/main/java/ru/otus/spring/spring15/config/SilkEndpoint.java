package ru.otus.spring.spring15.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.spring15.domain.Caterpillar;
import ru.otus.spring.spring15.domain.SilkCocoon;

import java.util.Collection;

@MessagingGateway
public interface SilkEndpoint {
    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "silkChannel")
    Collection<SilkCocoon> process(Collection<Caterpillar> caterpillars);

}
