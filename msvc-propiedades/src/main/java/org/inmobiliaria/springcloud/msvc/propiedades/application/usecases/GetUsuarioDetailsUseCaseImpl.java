package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.GetUsuarioDetailsUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.ExternalServicePort;

import java.util.Optional;

public class GetUsuarioDetailsUseCaseImpl implements GetUsuarioDetailsUseCase {
    private final ExternalServicePort externalServicePort;

    public GetUsuarioDetailsUseCaseImpl(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }


    @Override
    public Optional<UsuarioDetails> getUsuarioDetails(Long id) {
        return externalServicePort.getUsuarioDetails(id);
    }
}
