package com.dulceyson.juezVirtual.application.services;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;
import com.dulceyson.juezVirtual.domain.ports.in.*;
import com.dulceyson.juezVirtual.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, CreateUsuarioUseCase, RetrieveUsuarioUseCase,
        UpdateUsuarioUseCase, DeleteUsuarioUseCase, GetAdditionalUsuarioInfoUseCase {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final RetrieveUsuarioUseCase retrieveUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;
    private final GetAdditionalUsuarioInfoUseCase getAdditionalUsuarioInfoUseCase;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepositoryPort usuarioRepositoryPort; // 🔥 Adaptador correcto

    @Autowired
    public UsuarioService(CreateUsuarioUseCase createUsuarioUseCase, RetrieveUsuarioUseCase retrieveUsuarioUseCase,
                          UpdateUsuarioUseCase updateUsuarioUseCase, DeleteUsuarioUseCase deleteUsuarioUseCase,
                          GetAdditionalUsuarioInfoUseCase getAdditionalUsuarioInfoUseCase, PasswordEncoder passwordEncoder,
                          UsuarioRepositoryPort usuarioRepositoryPort) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.retrieveUsuarioUseCase = retrieveUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
        this.getAdditionalUsuarioInfoUseCase = getAdditionalUsuarioInfoUseCase;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepositoryPort = usuarioRepositoryPort; // 🔥 Usamos el puerto correcto
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = retrieveUsuarioUseCase.getUsuarioByUsername(username);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        Usuario usuario = usuarioOpt.get();
        Set<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role))
                .collect(Collectors.toSet());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        Optional<Usuario> existingUser = retrieveUsuarioUseCase.getUsuarioByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("El usuario ya existe con ese email.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRoles(Set.of("ROLE_USER")); // Asigna el rol por defecto
        return createUsuarioUseCase.createUsuario(usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return deleteUsuarioUseCase.deleteUsuario(id);
    }

    @Override
    public AdditionalUsuarioInfo getadditionalUsuarioInfo(Long id) {
        return getAdditionalUsuarioInfoUseCase.getadditionalUsuarioInfo(id);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return retrieveUsuarioUseCase.getUsuario(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return retrieveUsuarioUseCase.getAllUsuarios();
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario updateUsuario) {
        return updateUsuarioUseCase.updateUsuario(id, updateUsuario);
    }

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return retrieveUsuarioUseCase.getUsuarioByEmail(email);
    }

    @Override
    public Optional<Usuario> getUsuarioByUsername(String username) {
        return retrieveUsuarioUseCase.getUsuarioByUsername(username);
    }

    // **Actualizar rol de usuario**
    public void updateUserRole(Long userId, String role) {
        Optional<Usuario> usuarioOpt = retrieveUsuarioUseCase.getUsuario(userId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalStateException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setRoles(Set.of(role)); // Cambia el rol del usuario
        usuarioRepositoryPort.update(usuario); // 🔥 Usamos el adaptador correcto
    }

    // **Actualizar todos los datos de usuario (incluye estado activo)**
    public void updateUser(Long userId, String username, String email, String affiliation,
                           String mail, String policy, String url, String country, boolean activo) {
        Optional<Usuario> usuarioOpt = retrieveUsuarioUseCase.getUsuario(userId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalStateException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setAffiliation(affiliation);
        usuario.setMail(mail);
        usuario.setPolicy(policy);
        usuario.setUrl(url);
        usuario.setCountry(country);
        usuario.setActivo(activo); // 🔥 Nuevo: Actualizar estado activo

        System.out.println("🔍 Antes de actualizar: " + usuario); // Depuración antes de guardar
        usuarioRepositoryPort.update(usuario); // 🔥 Guardar correctamente con el puerto de repositorio
        System.out.println("✅ Después de actualizar: " + usuario); // Depuración después de guardar
    }
}