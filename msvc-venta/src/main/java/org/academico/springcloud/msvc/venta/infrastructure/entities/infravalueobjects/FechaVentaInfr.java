package org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FechaVentaInfr {
    private int dia;
    private int mes;
    private int anio;

    public FechaVentaInfr() {}

    public FechaVentaInfr(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaVentaInfr that = (FechaVentaInfr) o;
        return dia == that.dia && mes == that.mes && anio == that.anio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, mes, anio);
    }
}