package com.dulceyson.juezVirtual.infrastructure.controllers;

import com.dulceyson.juezVirtual.application.usecases.SendQuestionUseCase;
import com.dulceyson.juezVirtual.domain.dtos.ChatMessageDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/ask")
public class ChatController {

    private final SendQuestionUseCase enviarPreguntaUseCase;

    public ChatController(SendQuestionUseCase enviarPreguntaUseCase) {
        this.enviarPreguntaUseCase = enviarPreguntaUseCase;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> enviarPregunta(@RequestBody ChatMessageDTO payload) {
        try {
            String respuesta = enviarPreguntaUseCase.execute(payload);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
