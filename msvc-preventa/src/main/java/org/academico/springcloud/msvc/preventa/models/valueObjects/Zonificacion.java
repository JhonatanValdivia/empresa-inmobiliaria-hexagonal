package org.academico.springcloud.msvc.preventa.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Zonificacion {
    private String tipoZona;
    private String descripcionNormativa;
    private String usoPermitido;

    protected Zonificacion() {
        // Constructor protegido para JPA
    }

    @JsonCreator
    public Zonificacion(
            @JsonProperty("tipoZona") String tipoZona,
            @JsonProperty("descripcionNormativa") String descripcionNormativa,
            @JsonProperty("usoPermitido") String usoPermitido) {

        if (tipoZona == null || tipoZona.isBlank()) {
            throw new IllegalArgumentException("El tipo de zona no puede ser vacío");
        }
        if (descripcionNormativa == null || descripcionNormativa.isBlank()) {
            throw new IllegalArgumentException("La descripción normativa no puede ser vacía");
        }
        if (usoPermitido == null || usoPermitido.isBlank()) {
            throw new IllegalArgumentException("El uso permitido no puede ser vacío");
        }

        this.tipoZona = tipoZona;
        this.descripcionNormativa = descripcionNormativa;
        this.usoPermitido = usoPermitido;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public String getDescripcionNormativa() {
        return descripcionNormativa;
    }

    public String getUsoPermitido() {
        return usoPermitido;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Zonificacion))
            return false;
        Zonificacion that = (Zonificacion) object;
        return tipoZona.equals(that.tipoZona) &&
                descripcionNormativa.equals(that.descripcionNormativa) &&
                usoPermitido.equals(that.usoPermitido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoZona, descripcionNormativa, usoPermitido);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Uso: %s)", tipoZona, descripcionNormativa, usoPermitido);
    }
}