package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.domain.ports.in.RetrieveUsuarioUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveUsuarioUseCaseImplement implements RetrieveUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public RetrieveUsuarioUseCaseImplement(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepositoryPort.findByEmail(email);
    }

    @Override
    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepositoryPort.findByUsername(username);
    }

}