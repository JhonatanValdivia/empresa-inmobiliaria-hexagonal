package org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class FechaPagoComisionVO {
    private int dia;
    private int mes;
    private int anio;

    protected FechaPagoComisionVO() {
    }

    @JsonCreator
    public FechaPagoComisionVO(@JsonProperty("dia") int dia,
                               @JsonProperty("mes") int mes,
                               @JsonProperty("anio") int anio) {

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
}
