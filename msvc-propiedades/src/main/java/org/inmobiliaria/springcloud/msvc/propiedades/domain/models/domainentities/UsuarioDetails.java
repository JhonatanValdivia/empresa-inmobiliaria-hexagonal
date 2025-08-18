package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

public class UsuarioDetails {
    private final Long usuarioId;
    private final String nombre;

    public UsuarioDetails(Long usuarioId, String nombre) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNombre() {
        return nombre;
    }
}
