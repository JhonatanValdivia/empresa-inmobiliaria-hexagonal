package org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class FechaCobroInfr {
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minuto;

    public FechaCobroInfr() {}

    public FechaCobroInfr(int dia, int mes, int anio, int hora, int minuto) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }
    public int getAnio() {
        return anio;
    }



    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d %02d:%02d", dia, mes, anio, hora, minuto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaCobroInfr that = (FechaCobroInfr) o;
        return dia == that.dia && mes == that.mes && anio == that.anio && hora == that.hora && minuto == that.minuto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, mes, anio, hora, minuto);
    }
}