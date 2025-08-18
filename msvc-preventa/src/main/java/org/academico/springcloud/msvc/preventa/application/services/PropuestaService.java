package org.academico.springcloud.msvc.preventa.application.services;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class PropuestaService implements
        AceptarPropuestaPagoCasoUso,
        ActualizarPropuestaPagoCasoUso,
        AgregarPropuestaPagoCasoUso,
        ListarPropuestasPagoCasoUso,
        ObtenerPropuestaPagoPorIdCasoUso {

    private final AceptarPropuestaPagoCasoUso aceptarPropuestaPagoCasoUso;
    private final ActualizarPropuestaPagoCasoUso actualizarPropuestaPagoCasoUso;
    private final AgregarPropuestaPagoCasoUso agregarPropuestaPagoCasoUso;
    private final ListarPropuestasPagoCasoUso listarPropuestasPagoCasoUso;
    private final ObtenerPropuestaPagoPorIdCasoUso obtenerPropuestaPagoPorIdCasoUso;

    @Autowired
    public PropuestaService(
            AceptarPropuestaPagoCasoUso aceptarPropuestaPagoCasoUso,
            ActualizarPropuestaPagoCasoUso actualizarPropuestaPagoCasoUso,
            AgregarPropuestaPagoCasoUso agregarPropuestaPagoCasoUso,
            ListarPropuestasPagoCasoUso listarPropuestasPagoCasoUso,
            ObtenerPropuestaPagoPorIdCasoUso obtenerPropuestaPagoPorIdCasoUso) {
        this.aceptarPropuestaPagoCasoUso = aceptarPropuestaPagoCasoUso;
        this.actualizarPropuestaPagoCasoUso = actualizarPropuestaPagoCasoUso;
        this.agregarPropuestaPagoCasoUso = agregarPropuestaPagoCasoUso;
        this.listarPropuestasPagoCasoUso = listarPropuestasPagoCasoUso;
        this.obtenerPropuestaPagoPorIdCasoUso = obtenerPropuestaPagoPorIdCasoUso;
    }

    @Override
    public Optional<PropuestaPago> aceptarPropuestaPagoPreventa(Long preventaId, Long propuestaId) {
        return aceptarPropuestaPagoCasoUso.aceptarPropuestaPagoPreventa(preventaId, propuestaId);
    }

    @Override
    public Optional<PropuestaPago> actualizarPropuestaPago(Long preventaId, Long propuestaId, PropuestaPago propuestaActualizada) {
        return actualizarPropuestaPagoCasoUso.actualizarPropuestaPago(preventaId, propuestaId, propuestaActualizada);
    }

    @Override
    public Optional<Preventa> agregarPropuestaPago(Long preventaId, PropuestaPago propuesta) {
        return agregarPropuestaPagoCasoUso.agregarPropuestaPago(preventaId, propuesta);
    }

    @Override
    public Optional<List<PropuestaPago>> listarPropuestasPagoPorPreventa(Long preventaId) {
        return listarPropuestasPagoCasoUso.listarPropuestasPagoPorPreventa(preventaId);
    }

    @Override
    public Optional<PropuestaPago> porIdPropuestaPago(Long preventaId, Long propuestaId) {
        return obtenerPropuestaPagoPorIdCasoUso.porIdPropuestaPago(preventaId, propuestaId);
    }
}