package ru.otus.spring.spring15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring15.domain.Caterpillar;
import ru.otus.spring.spring15.domain.SilkCocoon;

import java.util.Collection;

@Configuration
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@EnableIntegration
public class SilkFlow {
    @Bean
    public QueueChannel caterpillarChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public PublishSubscribeChannel silkChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }
    @Bean
    public IntegrationFlow silkFlowProcess() {
        return IntegrationFlows.from( "caterpillarChannel" )
                .split()
                .handle( "caterpillarService", "metamorphosis" )
                .handle( "butterflyService", "butterflyOut" )
                .aggregate()
                .channel( "silkChannel" )
                .get();
    }
}
