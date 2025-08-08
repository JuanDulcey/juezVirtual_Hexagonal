package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.domain.ports.in.CreateUsuarioUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;

public class CreateUsuarioUseCaseImplement implements CreateUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public CreateUsuarioUseCaseImplement(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }
}
