package org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class TelefonoVO {
    private String numero;
    private String codigoPais;

    protected TelefonoVO() {}

    @JsonCreator
    public TelefonoVO(@JsonProperty("numero") String numero,
                      @JsonProperty("codigoPais") String codigoPais) {
        this.numero = numero;
        this.codigoPais = codigoPais;
    }

    public String getNumero() { return numero; }
    public String getCodigoPais() { return codigoPais; }
}