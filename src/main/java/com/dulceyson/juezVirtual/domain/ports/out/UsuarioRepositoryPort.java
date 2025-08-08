package com.dulceyson.juezVirtual.domain.ports.out;

import com.dulceyson.juezVirtual.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    Optional<Usuario> update(Usuario usuario);
    boolean deleteById(Long id);
    Optional<Usuario> findByEmail(String email); // Agregado
    Optional<Usuario> findByUsername(String username); // Agregado
}