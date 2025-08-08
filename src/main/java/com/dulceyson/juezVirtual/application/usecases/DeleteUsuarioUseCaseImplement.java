package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.ports.in.DeleteUsuarioUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;

public class DeleteUsuarioUseCaseImplement implements DeleteUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;


    public DeleteUsuarioUseCaseImplement(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioRepositoryPort.deleteById(id);
    }
}
