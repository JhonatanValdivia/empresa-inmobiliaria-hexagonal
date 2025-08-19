package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoUsuario;

public class UsuarioDetails {
    private final Long usuarioId;
    private final String nombre;
    private final TipoUsuario tipoUsuario;

    public boolean esPropietario() {
        return TipoUsuario.PROPIETARIO.equals(tipoUsuario);
    }
    public UsuarioDetails(Long usuarioId, String nombre, TipoUsuario tipoUsuario) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
