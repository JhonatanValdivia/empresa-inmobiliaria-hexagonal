package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa; // Se asume que usa el mismo enum

import java.time.LocalDate;
import java.util.Objects;

// Esta clase representa la información adicional que se obtiene de un servicio externo.
// Es un objeto de valor/entidad inmutable para el dominio de Preventa.
public class AdicionalPreventaInfo {
    private final Long userId;
    private final LocalDate userFechaInicio;
    private final EstadoPreventa userEstado;

    public AdicionalPreventaInfo(Long userId, LocalDate userFechaInicio, EstadoPreventa userEstado) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId para AdicionalPreventaInfo no puede ser nulo.");
        }
        if (userFechaInicio == null) {
            throw new IllegalArgumentException("Fecha de inicio para AdicionalPreventaInfo no puede ser nula.");
        }
        if (userEstado == null) {
            throw new IllegalArgumentException("Estado para AdicionalPreventaInfo no puede ser nulo.");
        }
        this.userId = userId;
        this.userFechaInicio = userFechaInicio;
        this.userEstado = userEstado;
    }

    // Constructor público vacío para deserialización (JSON/Feign)
    public AdicionalPreventaInfo() {
        this.userId = null;
        this.userFechaInicio = null;
        this.userEstado = null;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getUserFechaInicio() {
        return userFechaInicio;
    }

    public EstadoPreventa getUserEstado() {
        return userEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdicionalPreventaInfo that = (AdicionalPreventaInfo) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}