package com.dulceyson.juezVirtual.infrastructure.adapters;

import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;
import com.dulceyson.juezVirtual.domain.ports.out.ExternalServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceAdapter implements ExternalServicePort {

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public AdditionalUsuarioInfo getAdditionalUserInfo(Long usuarioId) {
        try {
            String userUrl = "https://api/usuarios/" + usuarioId;

            ResponseEntity<ApiUsuarioResponse> response =
                    restTemplate.getForEntity(userUrl, ApiUsuarioResponse.class);

            ApiUsuarioResponse user = response.getBody();

            if (user == null) {
                return null;
            }

            return new AdditionalUsuarioInfo(user.getId(), user.getUsername(), user.getEmail());

        } catch (Exception e) {
            // Aquí puedes loguear el error si quieres usar un logger
            return null;
        }
    }

    // Clase DTO interna para mapear la respuesta de la API externa
    private static class ApiUsuarioResponse {
        private Long id;
        private String username;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
