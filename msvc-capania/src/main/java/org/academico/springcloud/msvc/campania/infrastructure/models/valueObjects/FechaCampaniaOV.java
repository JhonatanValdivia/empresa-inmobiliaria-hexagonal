package org.academico.springcloud.msvc.campania.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class FechaCampaniaOV {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    protected FechaCampaniaOV() {}

    @JsonCreator
    public FechaCampaniaOV(@JsonProperty("fechaInicio") LocalDate fechaInicio,
                           @JsonProperty("fechaFin") LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
}
