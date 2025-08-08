package com.dulceyson.juezVirtual.infrastructure.repositories;

import com.dulceyson.juezVirtual.infrastructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    // Buscar usuario por nombre de usuario
    Optional<UsuarioEntity> findByUsername(String username);

    // Buscar usuario por email
    Optional<UsuarioEntity> findByEmail(String email);

    // Verificar si existe un usuario por su ID
    boolean existsById(Long id);

    // Eliminar usuario por ID
    void deleteById(Long id);

    // Obtener todos los usuarios
    List<UsuarioEntity> findAll();
}