package com.dulceyson.juezVirtual.domain.ports.out;

import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;

public interface ExternalServicePort {
    AdditionalUsuarioInfo getAdditionalUserInfo(Long usuarioId);
}
