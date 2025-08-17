package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.DeletePropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;

public class DeletePropiedadUseCaseImpl implements DeletePropiedadUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;

    public DeletePropiedadUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
    }

    @Override
    public boolean deletePropiedad(Long id) {
        return propiedadRepositoryPort.deleteById(id);
    }
}
