package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.domain.ports.in.UpdateUsuarioUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;

import java.util.Optional;

public class UpdateUsuarioUseCaseImplement implements UpdateUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UpdateUsuarioUseCaseImplement(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario updateUsuario) {
        // 1. Buscar el usuario existente
        return usuarioRepositoryPort.findById(id)
                .map(usuarioExistente -> {
                    // 2. Actualizar sólo los campos permitidos
                    usuarioExistente.setUsername(updateUsuario.getUsername());
                    usuarioExistente.setEmail(updateUsuario.getEmail());
                    usuarioExistente.setPassword(updateUsuario.getPassword());
                    // ... otros campos que necesites actualizar

                    // 3. Guardar los cambios
                    return usuarioRepositoryPort.save(usuarioExistente);
                });
    }
}
