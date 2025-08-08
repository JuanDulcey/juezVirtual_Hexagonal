package com.dulceyson.juezVirtual.domain.ports.in;

import com.dulceyson.juezVirtual.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface RetrieveUsuarioUseCase {
    Optional<Usuario> getUsuario(Long id);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioByEmail(String email);
    Optional<Usuario> getUsuarioByUsername(String email);
}