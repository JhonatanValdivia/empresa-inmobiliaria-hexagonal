package org.academico.springcloud.msvc.preventa.application.services;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VisitaService implements
        ActualizarEstadoVisitaCasoUso,
        ActualizarVisitaProgramadaCasoUso,
        AgregarVisitaProgramadaCasoUso,
        ListarVisitasProgramadasCasoUso,
        ReprogramarVisitaCasoUso {

    private final ActualizarEstadoVisitaCasoUso actualizarEstadoVisitaCasoUso;
    private final ActualizarVisitaProgramadaCasoUso actualizarVisitaProgramadaCasoUso;
    private final AgregarVisitaProgramadaCasoUso agregarVisitaProgramadaCasoUso;
    private final ListarVisitasProgramadasCasoUso listarVisitasProgramadasCasoUso;
    private final ReprogramarVisitaCasoUso reprogramarVisitaCasoUso;

    @Autowired
    public VisitaService(
            ActualizarEstadoVisitaCasoUso actualizarEstadoVisitaCasoUso,
            ActualizarVisitaProgramadaCasoUso actualizarVisitaProgramadaCasoUso,
            AgregarVisitaProgramadaCasoUso agregarVisitaProgramadaCasoUso,
            ListarVisitasProgramadasCasoUso listarVisitasProgramadasCasoUso,
            ReprogramarVisitaCasoUso reprogramarVisitaCasoUso) {
        this.actualizarEstadoVisitaCasoUso = actualizarEstadoVisitaCasoUso;
        this.actualizarVisitaProgramadaCasoUso = actualizarVisitaProgramadaCasoUso;
        this.agregarVisitaProgramadaCasoUso = agregarVisitaProgramadaCasoUso;
        this.listarVisitasProgramadasCasoUso = listarVisitasProgramadasCasoUso;
        this.reprogramarVisitaCasoUso = reprogramarVisitaCasoUso;
    }

    @Override
    public Optional<VisitaProgramada> actualizarEstadoVisitaPreventa(Long preventaId, Long visitaId, EstadoVisita estadoVisita) {
        return actualizarEstadoVisitaCasoUso.actualizarEstadoVisitaPreventa(preventaId, visitaId, estadoVisita);
    }

    @Override
    public Optional<VisitaProgramada> actualizarVisitaProgramada(Long preventaId, Long visitaId, VisitaProgramada visitaActualizada) {
        return actualizarVisitaProgramadaCasoUso.actualizarVisitaProgramada(preventaId, visitaId, visitaActualizada);
    }

    @Override
    public Optional<Preventa> agregarVisitaProgramada(Long preventaId, VisitaProgramada visita) {
        return agregarVisitaProgramadaCasoUso.agregarVisitaProgramada(preventaId, visita);
    }

    @Override
    public Optional<List<VisitaProgramada>> listarVisitasProgramadasPorPreventa(Long preventaId) {
        return listarVisitasProgramadasCasoUso.listarVisitasProgramadasPorPreventa(preventaId);
    }

    @Override
    public Optional<VisitaProgramada> reprogramarVisitaPreventa(Long preventaId, Long visitaId, LocalDate fecha) {
        return reprogramarVisitaCasoUso.reprogramarVisitaPreventa(preventaId, visitaId, fecha);
    }
}