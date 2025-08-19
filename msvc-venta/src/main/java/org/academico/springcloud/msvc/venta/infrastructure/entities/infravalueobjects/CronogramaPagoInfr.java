package org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;
import org.academico.springcloud.msvc.venta.domain.models.enums.FrecuenciaPago;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class CronogramaPagoInfr {
    private LocalDate fechaInicio;
    private int numeroCuotas;
    @Enumerated(EnumType.STRING)
    private FrecuenciaPago frecuencia;

    public CronogramaPagoInfr() {}

    public CronogramaPagoInfr(LocalDate fechaInicio, int numeroCuotas, FrecuenciaPago frecuencia) {
        this.fechaInicio = fechaInicio;
        this.numeroCuotas = numeroCuotas;
        this.frecuencia = frecuencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public FrecuenciaPago getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(FrecuenciaPago frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CronogramaPagoInfr that = (CronogramaPagoInfr) o;
        return numeroCuotas == that.numeroCuotas &&
                Objects.equals(fechaInicio, that.fechaInicio) &&
                frecuencia == that.frecuencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, numeroCuotas, frecuencia);
    }
}