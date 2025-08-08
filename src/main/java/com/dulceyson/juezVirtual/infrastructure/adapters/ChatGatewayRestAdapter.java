package com.dulceyson.juezVirtual.infrastructure.adapters;

import com.dulceyson.juezVirtual.domain.dtos.ChatMessageDTO;
import com.dulceyson.juezVirtual.domain.ports.out.ChatGateway;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ChatGatewayRestAdapter implements ChatGateway {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String sendMessage(ChatMessageDTO message) {
        String url = "http://localhost:5000/chat";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("message", message.getMessage()), headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            return (String) response.getBody().get("reply");
        } catch (Exception e) {
            throw new RuntimeException("Error al contactar al chatbot: " + e.getMessage(), e);
        }
    }
}
