package org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class CorreoElectronicoVO {
    private String dominio;
    private String valorCorreo;

    protected CorreoElectronicoVO() {}

    @JsonCreator
    public CorreoElectronicoVO(@JsonProperty("dominio") String dominio,
                               @JsonProperty("valorCorreo") String valorCorreo) {
        this.dominio = dominio;
        this.valorCorreo = valorCorreo;
    }

    public String getDominio() { return dominio; }
    public String getValorCorreo() { return valorCorreo; }
}