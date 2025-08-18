package org.academico.springcloud.msvc.norma.infrastructure.entities.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class FechaInfr {

    private Integer dia;
    private Integer mes;
    private Integer anio;

    public FechaInfr() {}

    public FechaInfr(Integer dia, Integer mes, Integer anio) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido");
        }
        if (anio < 1900) {
            throw new IllegalArgumentException("Año debe ser mayor a 1900");
        }
        if (dia < 1 || dia > 31) {
            throw new IllegalArgumentException("Día inválido");
        }

        try {
            LocalDate.of(anio, mes, dia); // Verifica la combinación fecha
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida");
        }

        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Integer getDia() {
        return dia;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FechaInfr)) return false;
        FechaInfr fecha = (FechaInfr) obj;
        return dia.equals(fecha.dia) && mes.equals(fecha.mes) && anio.equals(fecha.anio);
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, mes, anio);
    }
}
