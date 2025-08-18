package org.academico.springcloud.msvc.preventa.application.services;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class ContratoService implements
        ActualizarContratoVentaCasoUso,
        AgregarContratoCasoUso,
        AnularContratoPreventaCasoUso,
        EliminarContratoVentaCasoUso,
        ObtenerContratoPorPreventaCasoUso {

    private final ActualizarContratoVentaCasoUso actualizarContratoVentaCasoUso;
    private final AgregarContratoCasoUso agregarContratoCasoUso;
    private final AnularContratoPreventaCasoUso anularContratoPreventaCasoUso;
    private final EliminarContratoVentaCasoUso eliminarContratoVentaCasoUso;
    private final ObtenerContratoPorPreventaCasoUso obtenerContratoPorPreventaCasoUso;

    @Autowired
    public ContratoService(
            ActualizarContratoVentaCasoUso actualizarContratoVentaCasoUso,
            AgregarContratoCasoUso agregarContratoCasoUso,
            AnularContratoPreventaCasoUso anularContratoPreventaCasoUso,
            EliminarContratoVentaCasoUso eliminarContratoVentaCasoUso,
            ObtenerContratoPorPreventaCasoUso obtenerContratoPorPreventaCasoUso) {
        this.actualizarContratoVentaCasoUso = actualizarContratoVentaCasoUso;
        this.agregarContratoCasoUso = agregarContratoCasoUso;
        this.anularContratoPreventaCasoUso = anularContratoPreventaCasoUso;
        this.eliminarContratoVentaCasoUso = eliminarContratoVentaCasoUso;
        this.obtenerContratoPorPreventaCasoUso = obtenerContratoPorPreventaCasoUso;
    }

    @Override
    public Optional<ContratoVenta> actualizarContratoVenta(Long preventaId, ContratoVenta contratoActualizado) {
        return actualizarContratoVentaCasoUso.actualizarContratoVenta(preventaId, contratoActualizado);
    }

    @Override
    public Optional<ContratoVenta> agregarContratoVenta(Long preventaId, ContratoVenta contrato) {
        return agregarContratoCasoUso.agregarContratoVenta(preventaId, contrato);
    }

    @Override
    public Optional<ContratoVenta> anularContratoPreventa(Long preventaId) {
        return anularContratoPreventaCasoUso.anularContratoPreventa(preventaId);
    }

    @Override
    public Optional<Preventa> eliminarContratoVenta(Long preventaId) { // Retorna Preventa, no ContratoVenta
        return eliminarContratoVentaCasoUso.eliminarContratoVenta(preventaId);
    }

    @Override
    public Optional<ContratoVenta> obtenerContratoPorPreventa(Long preventaId) {
        return obtenerContratoPorPreventaCasoUso.obtenerContratoPorPreventa(preventaId);
    }
}