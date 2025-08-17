package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;

import java.util.Optional;

public interface GetUsuarioDetailsUseCase {
    Optional<UsuarioDetails> getUsuarioDetails(Long id);
}
