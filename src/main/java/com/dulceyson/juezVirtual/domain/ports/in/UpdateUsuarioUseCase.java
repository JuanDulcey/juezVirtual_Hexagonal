package com.dulceyson.juezVirtual.domain.ports.in;

import com.dulceyson.juezVirtual.domain.models.Usuario;

import java.util.Optional;

public interface UpdateUsuarioUseCase {
    Optional<Usuario> updateUsuario(Long id, Usuario updateUsuario);
}
