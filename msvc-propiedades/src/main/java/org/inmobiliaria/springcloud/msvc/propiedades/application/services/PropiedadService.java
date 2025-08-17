package org.inmobiliaria.springcloud.msvc.propiedades.application.services;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.CreatePropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.DeletePropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.GetPropiedadUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.UpdatePropiedadUseCase;

import java.util.List;
import java.util.Optional;

public class PropiedadService implements CreatePropiedadUseCase, DeletePropiedadUseCase, GetPropiedadUseCase, UpdatePropiedadUseCase {

    private final CreatePropiedadUseCase createPropiedadUseCase;
    private final DeletePropiedadUseCase deletePropiedadUseCase;
    private final GetPropiedadUseCase getPropiedadUseCase;
    private final UpdatePropiedadUseCase updatePropiedadUseCase;

    public PropiedadService(CreatePropiedadUseCase createPropiedadUseCase, DeletePropiedadUseCase deletePropiedadUseCase, GetPropiedadUseCase getPropiedadUseCase, UpdatePropiedadUseCase updatePropiedadUseCase) {
        this.createPropiedadUseCase = createPropiedadUseCase;
        this.deletePropiedadUseCase = deletePropiedadUseCase;
        this.getPropiedadUseCase = getPropiedadUseCase;
        this.updatePropiedadUseCase = updatePropiedadUseCase;
    }

    @Override
    public PropiedadInmobiliaria createPropiedad(PropiedadInmobiliaria propiedadInmobiliaria) {
        return createPropiedadUseCase.createPropiedad(propiedadInmobiliaria);
    }

    @Override
    public boolean deletePropiedad(Long id) {
        return deletePropiedadUseCase.deletePropiedad(id);
    }

    @Override
    public Optional<PropiedadInmobiliaria> getPropiedad(Long id) {
        return getPropiedadUseCase.getPropiedad(id);
    }

    @Override
    public List<PropiedadInmobiliaria> getAllPropiedades() {
        return getPropiedadUseCase.getAllPropiedades();
    }

    @Override
    public Optional<PropiedadInmobiliaria> updatePropiedad(Long id, PropiedadInmobiliaria propiedadInmobiliaria) {
        return updatePropiedadUseCase.updatePropiedad(id, propiedadInmobiliaria);
    }
}
