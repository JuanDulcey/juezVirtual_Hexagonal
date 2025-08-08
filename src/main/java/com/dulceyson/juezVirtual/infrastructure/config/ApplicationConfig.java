package com.dulceyson.juezVirtual.infrastructure.config;

import com.dulceyson.juezVirtual.application.services.UsuarioService;
import com.dulceyson.juezVirtual.application.usecases.*;
import com.dulceyson.juezVirtual.domain.ports.in.GetAdditionalUsuarioInfoUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.ExternalServicePort;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;
import com.dulceyson.juezVirtual.infrastructure.adapters.ExternalServiceAdapter;
import com.dulceyson.juezVirtual.infrastructure.repositories.JPAUsuarioRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    // Configuración del servicio de usuario con todas sus dependencias
    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort,
                                         GetAdditionalUsuarioInfoUseCase getAdditionalUsuarioInfoUseCase,
                                         PasswordEncoder passwordEncoder) {
        return new UsuarioService(
                new CreateUsuarioUseCaseImplement(usuarioRepositoryPort),
                new RetrieveUsuarioUseCaseImplement(usuarioRepositoryPort),
                new UpdateUsuarioUseCaseImplement(usuarioRepositoryPort),
                new DeleteUsuarioUseCaseImplement(usuarioRepositoryPort),
                getAdditionalUsuarioInfoUseCase,
                passwordEncoder,
                usuarioRepositoryPort
        );
    }

    // Configuración del repositorio de usuario
    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(JPAUsuarioRepositoryAdapter jpaUsuarioRepositoryAdapter) {
        return jpaUsuarioRepositoryAdapter;
    }

    // Configuración del caso de uso para obtener información adicional del usuario
    @Bean
    public GetAdditionalUsuarioInfoUseCase getAdditionalUsuarioInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalUsuarioInfoUseCaseImplement(externalServicePort);
    }

    // Configuración de la conexión con el servicio externo
    @Bean
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }

}