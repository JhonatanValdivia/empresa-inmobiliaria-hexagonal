package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.AsignarUsuarioUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.ExternalServicePort;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;

import java.util.Optional;

public class AsignarUsuarioUseCaseImpl implements AsignarUsuarioUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;
    private final ExternalServicePort externalServicePort;

    public AsignarUsuarioUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort, ExternalServicePort externalServicePort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
        this.externalServicePort = externalServicePort;
    }

    @Override
    public void asignarUsuario(Long propiedadId, Long usuarioId) {
        PropiedadInmobiliaria  propiedadInmobiliaria = propiedadRepositoryPort.findById(propiedadId)
                        .orElseThrow(() -> new IllegalArgumentException("Propiedad no existe"));



        externalServicePort.getUsuarioDetails(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

        propiedadInmobiliaria.asignarUsuario(usuarioId);
        propiedadRepositoryPort.save(propiedadInmobiliaria);

    }
}
