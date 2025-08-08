package com.dulceyson.juezVirtual.infrastructure.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final RestTemplate restTemplate = new RestTemplate();

    // Usuario y contraseña hardcodeados
    private final String hardcodedId = "dulceysonSAS";
    private final String hardcodedPassword = "DULCEYSON123";

    @PostMapping("/api/judge/login")
    public ResponseEntity<?> login() {
        String sessionUrl = "https://judgeapi.u-aizu.ac.jp/session";

        try {
            // Construir payload con usuario y contraseña hardcodeados
            Map<String, String> credentials = new HashMap<>();
            credentials.put("id", hardcodedId);
            credentials.put("password", hardcodedPassword);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials, headers);

            // Realizar POST a /session
            ResponseEntity<String> response = restTemplate.postForEntity(sessionUrl, entity, String.class);

            // Devuelve la cookie de sesión del header 'Set-Cookie'
            List<String> cookies = response.getHeaders().get("Set-Cookie");
            if (cookies != null && !cookies.isEmpty()) {
                String jsessionId = cookies.stream()
                        .filter(cookie -> cookie.startsWith("JSESSIONID"))
                        .findFirst()
                        .orElse("No JSESSIONID found");
                return ResponseEntity.ok("Cookie: " + jsessionId);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se recibió cookie de sesión");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en login: " + e.getMessage());
        }
    }
}
