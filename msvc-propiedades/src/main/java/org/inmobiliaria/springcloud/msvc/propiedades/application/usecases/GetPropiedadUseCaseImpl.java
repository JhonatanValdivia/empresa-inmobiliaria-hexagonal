package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.GetPropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;

import java.util.List;
import java.util.Optional;

public class GetPropiedadUseCaseImpl implements GetPropiedadUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;

    public GetPropiedadUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
    }

    @Override
    public Optional<PropiedadInmobiliaria> getPropiedad(Long id) {
        return propiedadRepositoryPort.findById(id);
    }

    @Override
    public List<PropiedadInmobiliaria> getAllPropiedades() {
        return propiedadRepositoryPort.findAll();
    }
}
