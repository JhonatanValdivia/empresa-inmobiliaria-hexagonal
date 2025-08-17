package org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class NombreCompletoVO {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    protected NombreCompletoVO() {}

    @JsonCreator
    public NombreCompletoVO(@JsonProperty("primerNombre") String primerNombre,
                            @JsonProperty("segundoNombre") String segundoNombre,
                            @JsonProperty("primerApellido") String primerApellido,
                            @JsonProperty("segundoApellido") String segundoApellido) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() { return primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public String getPrimerApellido() { return primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
}