package com.dulceyson.juezVirtual.domain.ports.in;

import com.dulceyson.juezVirtual.domain.models.Usuario;

public interface CreateUsuarioUseCase {
    Usuario createUsuario(Usuario usuario);
}
