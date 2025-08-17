package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.UpdatePropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;

import java.util.Optional;

public class UpdatePropiedadUseCaseImpl implements UpdatePropiedadUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;

    public UpdatePropiedadUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
    }


    @Override
    public Optional<PropiedadInmobiliaria> updatePropiedad(Long id, PropiedadInmobiliaria propiedadInmobiliaria) {
        return propiedadRepositoryPort.update(propiedadInmobiliaria);
    }
}
