package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita;

import java.time.LocalDate;
import java.util.Objects;

public class VisitaProgramada {
    private Long id;
    private LocalDate fecha;
    private EstadoVisita estadoVisita;
    // Referencia a Preventa gestionada por el agregado

    public VisitaProgramada() {
        this.estadoVisita = EstadoVisita.PROGRAMADA; // Estado inicial por defecto
    }

    public VisitaProgramada(Long id, LocalDate fecha, EstadoVisita estadoVisita) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la visita es obligatoria.");
        }
        this.id = id;
        this.fecha = fecha;
        this.estadoVisita = (estadoVisita != null) ? estadoVisita : EstadoVisita.PROGRAMADA;
    }

    // Lógica de dominio
    public void reprogramarVisita(LocalDate nuevaFecha) {
        if (this.estadoVisita == EstadoVisita.PROGRAMADA) {
            if (nuevaFecha == null || nuevaFecha.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La nueva fecha no puede ser nula ni pasada.");
            }
            this.fecha = nuevaFecha;
            System.out.println("Visita reprogramada para: " + nuevaFecha);
        } else {
            throw new IllegalStateException("Solo se pueden reprogramar visitas PROGRAMADAS. Estado actual: " + this.estadoVisita);
        }
    }

    public void actualizarEstado(EstadoVisita nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }
        this.estadoVisita = nuevoEstado;
        System.out.println("Estado de visita actualizado a: " + nuevoEstado);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public EstadoVisita getEstadoVisita() { return estadoVisita; }
    public void setEstadoVisita(EstadoVisita estadoVisita) { this.estadoVisita = estadoVisita; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitaProgramada that = (VisitaProgramada) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}