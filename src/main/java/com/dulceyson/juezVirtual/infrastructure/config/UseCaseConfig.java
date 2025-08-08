package com.dulceyson.juezVirtual.infrastructure.config;

import com.dulceyson.juezVirtual.application.usecases.SendQuestionUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.ChatGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SendQuestionUseCase sendQuestionUseCase(ChatGateway chatGateway) {
        return new SendQuestionUseCase(chatGateway);
    }
}
