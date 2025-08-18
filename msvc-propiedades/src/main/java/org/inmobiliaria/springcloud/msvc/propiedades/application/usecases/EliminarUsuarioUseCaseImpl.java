package org.inmobiliaria.springcloud.msvc.propiedades.application.usecases;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.EliminarUsuarioUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;

public class EliminarUsuarioUseCaseImpl implements EliminarUsuarioUseCase {
    private final PropiedadRepositoryPort propiedadRepositoryPort;

    public EliminarUsuarioUseCaseImpl(PropiedadRepositoryPort propiedadRepositoryPort) {
        this.propiedadRepositoryPort = propiedadRepositoryPort;
    }

    @Override
    public void eliminarUsuario(Long propiedadId) {

        PropiedadInmobiliaria propiedadInmobiliaria = propiedadRepositoryPort.findById(propiedadId)
                .orElseThrow(() -> new IllegalArgumentException("Propiedad no existe"));

        propiedadInmobiliaria.quitarUsuario();
        propiedadRepositoryPort.save(propiedadInmobiliaria);



    }
}
