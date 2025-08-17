package org.academico.springcloud.msvc.preventa.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;


@Embeddable
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    protected Fecha() {
    }

    @JsonCreator
    public Fecha(@JsonProperty("dia") int dia,
                 @JsonProperty("mes") int mes,
                 @JsonProperty("anio") int anio) {

        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        if (anio < 1900) {
            throw new IllegalArgumentException("Año inválido: debe ser mayor a 1900");
        }
        if (dia < 1 || dia > 31) {
            throw new IllegalArgumentException("Día inválido: debe estar entre 1 y 31");
        }

        try {
            LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida: combinación día-mes-año incorrecta");
        }

        if (LocalDate.of(anio, mes, dia).isBefore(LocalDate.now())) {
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
        return dia + "/" + mes + "/" + anio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fecha)) return false;
        Fecha fecha = (Fecha) obj;
        return dia == fecha.dia && mes == fecha.mes && anio == fecha.anio;
    }

    @Override
    public int hashCode() {

        return Objects.hash(dia, mes, anio);
    }
}