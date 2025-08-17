package org.academico.springcloud.msvc.usuario.infrastructure.mappers;

import org.academico.springcloud.msvc.usuario.domain.model.*;
import org.academico.springcloud.msvc.usuario.domain.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.infrastructure.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;

        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());

        usuario.setNombreCompleto(entity.getNombreCompleto());
        usuario.setTipoUsuario(entity.getTipoUsuario());
        usuario.setTelefono(entity.getTelefono());
        usuario.setCorreoElectronico(entity.getCorreoElectronico());
        usuario.setDireccion(entity.getDireccion());

        return usuario;
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());

        entity.setNombreCompleto(usuario.getNombreCompleto());
        entity.setTipoUsuario(usuario.getTipoUsuario());
        entity.setTelefono(usuario.getTelefono());
        entity.setCorreoElectronico(usuario.getCorreoElectronico());
        entity.setDireccion(usuario.getDireccion());

        return entity;
    }
}