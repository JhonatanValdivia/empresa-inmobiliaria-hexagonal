package org.inmobiliaria.springcloud.msvc.propiedades.application.services;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.*;

import java.util.List;
import java.util.Optional;

public class PropiedadService implements CreatePropiedadUseCase, DeletePropiedadUseCase, GetPropiedadUseCase, UpdatePropiedadUseCase , GetUsuarioDetailsUseCase ,AsignarUsuarioUseCase, EliminarUsuarioUseCase{

    private final CreatePropiedadUseCase createPropiedadUseCase;
    private final DeletePropiedadUseCase deletePropiedadUseCase;
    private final GetPropiedadUseCase getPropiedadUseCase;
    private final UpdatePropiedadUseCase updatePropiedadUseCase;
    private final GetUsuarioDetailsUseCase getUsuarioDetailsUseCase;
    private final AsignarUsuarioUseCase asignarUsuarioUseCase;
    private final EliminarUsuarioUseCase eliminarUsuarioUseCase;

    public PropiedadService(CreatePropiedadUseCase createPropiedadUseCase, DeletePropiedadUseCase deletePropiedadUseCase, GetPropiedadUseCase getPropiedadUseCase, UpdatePropiedadUseCase updatePropiedadUseCase, GetUsuarioDetailsUseCase getUsuarioDetailsUseCase, AsignarUsuarioUseCase asignarUsuarioUseCase, EliminarUsuarioUseCase eliminarUsuarioUseCase) {
        this.createPropiedadUseCase = createPropiedadUseCase;
        this.deletePropiedadUseCase = deletePropiedadUseCase;
        this.getPropiedadUseCase = getPropiedadUseCase;
        this.updatePropiedadUseCase = updatePropiedadUseCase;
        this.getUsuarioDetailsUseCase = getUsuarioDetailsUseCase;
        this.asignarUsuarioUseCase = asignarUsuarioUseCase;
        this.eliminarUsuarioUseCase = eliminarUsuarioUseCase;
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


    @Override
    public Optional<UsuarioDetails> getUsuarioDetails(Long id) {
        return getUsuarioDetailsUseCase.getUsuarioDetails(id);
    }

    @Override
    public void asignarUsuario(Long propiedadId, Long usuarioId) {
        asignarUsuarioUseCase.asignarUsuario(propiedadId,usuarioId);
    }

    @Override
    public void eliminarUsuario(Long propiedadId) {
        eliminarUsuarioUseCase.eliminarUsuario(propiedadId);

    }
}
