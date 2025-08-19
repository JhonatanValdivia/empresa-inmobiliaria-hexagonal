package org.academico.springcloud.msvc.venta.domain.models.valueobjects;

import org.academico.springcloud.msvc.venta.domain.models.enums.FrecuenciaPago;

import java.time.LocalDate;
import java.util.Objects;

public class CronogramaPago {
    private final LocalDate fechaInicio;
    private final int numeroCuotas;
    private final FrecuenciaPago frecuencia;

    protected CronogramaPago() { // Constructor protegido para frameworks o deserialización controlada si fuera necesario
        this.fechaInicio = null;
        this.numeroCuotas = 0;
        this.frecuencia = null;
    }

    public CronogramaPago(LocalDate fechaInicio, int numeroCuotas, FrecuenciaPago frecuencia) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser pasada.");
        }
        if (numeroCuotas <= 0) {
            throw new IllegalArgumentException("El número de cuotas debe ser mayor que cero.");
        }
        if (frecuencia == null) {
            throw new IllegalArgumentException("La frecuencia no puede ser nula o vacía.");
        }

        this.fechaInicio = fechaInicio;
        this.numeroCuotas = numeroCuotas;
        this.frecuencia = frecuencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public FrecuenciaPago getFrecuencia() {
        return frecuencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CronogramaPago that = (CronogramaPago) o;
        return numeroCuotas == that.numeroCuotas &&
                Objects.equals(fechaInicio, that.fechaInicio) &&
                frecuencia == that.frecuencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, numeroCuotas, frecuencia);
    }

    @Override
    public String toString() {
        return "Inicio de pago: " + fechaInicio +
                ",cuotas:" + numeroCuotas + ",frecuencia:" + frecuencia;
    }
}