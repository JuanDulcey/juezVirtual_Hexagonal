package com.dulceyson.juezVirtual.domain.ports.out;

import com.dulceyson.juezVirtual.domain.dtos.ChatMessageDTO;

public interface ChatGateway {
    String sendMessage(ChatMessageDTO message);
}
