package org.academico.springcloud.msvc.venta.domain.models.valueobjects;

import java.time.LocalDate;
import java.util.Objects;

public class FechaVenta {
    private final int dia;
    private final int mes;
    private final int anio;

    protected FechaVenta() {
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;
    }

    public FechaVenta(int dia, int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        if (anio < 1900) {
            throw new IllegalArgumentException("Año inválido: debe ser mayor a 1900");
        }
        if (dia < 1 || dia > 31) {
            throw new IllegalArgumentException("Día inválido: debe estar entre 1 y 31");
        }

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida: combinación día-mes-año incorrecta");
        }

        if (parsedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha inválida: no puede ser en el pasado");
        }

        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
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

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dia, mes, anio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaVenta that = (FechaVenta) o;
        return dia == that.dia && mes == that.mes && anio == that.anio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, mes, anio);
    }
}