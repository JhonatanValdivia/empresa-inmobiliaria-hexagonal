package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.CreatePropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;

public class CreatePropiedadUseCaseImpl implements CreatePropiedadUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;

    public CreatePropiedadUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
    }

    @Override
    public PropiedadInmobiliaria createPropiedad(PropiedadInmobiliaria propiedadInmobiliaria) {
        return propiedadRepositoryPort.save(propiedadInmobiliaria);
    }
}
