package com.dulceyson.juezVirtual.application.usecases;

import com.dulceyson.juezVirtual.domain.models.AdditionalUsuarioInfo;
import com.dulceyson.juezVirtual.domain.ports.in.GetAdditionalUsuarioInfoUseCase;
import com.dulceyson.juezVirtual.domain.ports.out.ExternalServicePort;

public class GetAdditionalUsuarioInfoUseCaseImplement implements GetAdditionalUsuarioInfoUseCase {

    private final ExternalServicePort externalServicePort;


    public GetAdditionalUsuarioInfoUseCaseImplement(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    @Override
    public AdditionalUsuarioInfo getadditionalUsuarioInfo(Long id) {
        return externalServicePort.getAdditionalUserInfo(id);
    }
}
