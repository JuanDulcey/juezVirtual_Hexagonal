package com.dulceyson.juezVirtual.infrastructure.controllers;

import com.dulceyson.juezVirtual.application.services.UsuarioService;
import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;
import com.dulceyson.juezVirtual.domain.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControllerConsola {

    private final UsuarioService usuarioService;

    public UsuarioControllerConsola(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Obtener un usuario por ID
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioService.getUsuario(usuarioId);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Actualizar un usuario con los nuevos datos
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long usuarioId, @RequestBody Usuario updatedUsuario) {
        Optional<Usuario> usuario = usuarioService.updateUsuario(usuarioId, updatedUsuario);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar usuario
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long usuarioId) {
        if (usuarioService.deleteUsuario(usuarioId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener información adicional del usuario
    @GetMapping("/{usuarioId}/additional-info")
    public ResponseEntity<AdditionalUsuarioInfo> getAdditionalUsuarioInfo(@PathVariable Long usuarioId) {
        AdditionalUsuarioInfo additionalInfo = usuarioService.getadditionalUsuarioInfo(usuarioId);
        return new ResponseEntity<>(additionalInfo, HttpStatus.OK);
    }

}