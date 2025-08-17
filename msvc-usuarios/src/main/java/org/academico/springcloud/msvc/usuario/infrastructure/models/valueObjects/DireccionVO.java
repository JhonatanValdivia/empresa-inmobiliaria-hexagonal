package org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class DireccionVO {
    private String ubigeo;
    private String ciudad;
    private String direccion;

    protected DireccionVO() {}

    @JsonCreator
    public DireccionVO(@JsonProperty("ubigeo") String ubigeo,
                       @JsonProperty("ciudad") String ciudad,
                       @JsonProperty("direccion") String direccion) {
        this.ubigeo = ubigeo;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getUbigeo() { return ubigeo; }
    public String getCiudad() { return ciudad; }
    public String getDireccion() { return direccion; }
}