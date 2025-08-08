package com.dulceyson.juezVirtual.infrastructure.repositories;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;
import com.dulceyson.juezVirtual.infrastructure.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPAUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JPAUsuarioRepository jpaUsuarioRepository;

    public JPAUsuarioRepositoryAdapter(JPAUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity savedUsuarioEntity = jpaUsuarioRepository.save(usuarioEntity);
        return savedUsuarioEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaUsuarioRepository.findById(id)
                .map(UsuarioEntity::toDomainModel);
    }

    @Override
    public List<Usuario> findAll() {
        return jpaUsuarioRepository.findAll().stream()
                .map(UsuarioEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {
        if (jpaUsuarioRepository.existsById(usuario.getId())) {
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updateUsuarioEntity = jpaUsuarioRepository.save(usuarioEntity);
            return Optional.of(updateUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (jpaUsuarioRepository.existsById(id)) {
            jpaUsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaUsuarioRepository.findByEmail(email)
                .map(UsuarioEntity::toDomainModel);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return jpaUsuarioRepository.findByUsername(username)
                .map(UsuarioEntity::toDomainModel);
    }
}