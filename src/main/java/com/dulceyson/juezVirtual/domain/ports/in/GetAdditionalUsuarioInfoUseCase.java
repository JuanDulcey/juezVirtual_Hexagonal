package com.dulceyson.juezVirtual.domain.ports.in;

import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;

public interface GetAdditionalUsuarioInfoUseCase {

    AdditionalUsuarioInfo getadditionalUsuarioInfo(Long id);
}
