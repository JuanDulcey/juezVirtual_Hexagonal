package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.dtos.ChatMessageDTO;
import com.dulceyson.juezVirtual.domain.ports.out.ChatGateway;

public class SendQuestionUseCase {

    private final ChatGateway chatGateway;

    public SendQuestionUseCase(ChatGateway chatGateway) {
        this.chatGateway = chatGateway;
    }

    public String execute(ChatMessageDTO message) {
        return chatGateway.sendMessage(message);
    }
}
