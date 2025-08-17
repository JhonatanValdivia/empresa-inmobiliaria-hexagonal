package org.academico.springcloud.msvc.usuario.infrastructure.mappers;

import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.models.valueObjects.CorreoElectronico;
import org.academico.springcloud.msvc.usuario.domain.models.valueObjects.Direccion;
import org.academico.springcloud.msvc.usuario.domain.models.valueObjects.NombreCompleto;
import org.academico.springcloud.msvc.usuario.domain.models.valueObjects.Telefono;
import org.academico.springcloud.msvc.usuario.infrastructure.models.entities.UsuarioEntity;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.CorreoElectronicoVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.DireccionVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.NombreCompletoVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.TelefonoVO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;

        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());

        if (entity.getNombreCompleto() != null) {
            usuario.setNombreCompleto(new NombreCompleto(
                    entity.getNombreCompleto().getPrimerNombre(),
                    entity.getNombreCompleto().getSegundoNombre(),
                    entity.getNombreCompleto().getPrimerApellido(),
                    entity.getNombreCompleto().getSegundoApellido()
            ));
        }

        usuario.setTipoUsuario(entity.getTipoUsuario());

        if (entity.getTelefono() != null) {
            usuario.setTelefono(new Telefono(
                    entity.getTelefono().getNumero(),
                    entity.getTelefono().getCodigoPais()
            ));
        }

        if (entity.getCorreoElectronico() != null) {
            usuario.setCorreoElectronico(new CorreoElectronico(
                    entity.getCorreoElectronico().getDominio(),
                    entity.getCorreoElectronico().getValorCorreo()
            ));
        }

        if (entity.getDireccion() != null) {
            usuario.setDireccion(new Direccion(
                    entity.getDireccion().getUbigeo(),
                    entity.getDireccion().getCiudad(),
                    entity.getDireccion().getDireccion()
            ));
        }

        return usuario;
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());

        if (usuario.getNombreCompleto() != null) {
            entity.setNombreCompleto(new NombreCompletoVO(
                    usuario.getNombreCompleto().getPrimerNombre(),
                    usuario.getNombreCompleto().getSegundoNombre(),
                    usuario.getNombreCompleto().getPrimerApellido(),
                    usuario.getNombreCompleto().getSegundoApellido()
            ));
        }

        entity.setTipoUsuario(usuario.getTipoUsuario());

        if (usuario.getTelefono() != null) {
            entity.setTelefono(new TelefonoVO(
                    usuario.getTelefono().getNumero(),
                    usuario.getTelefono().getCodigoPais()
            ));
        }

        if (usuario.getCorreoElectronico() != null) {
            entity.setCorreoElectronico(new CorreoElectronicoVO(
                    usuario.getCorreoElectronico().getDominio(),
                    usuario.getCorreoElectronico().getValorCorreo()
            ));
        }

        if (usuario.getDireccion() != null) {
            entity.setDireccion(new DireccionVO(
                    usuario.getDireccion().getUbigeo(),
                    usuario.getDireccion().getCiudad(),
                    usuario.getDireccion().getDireccion()
            ));
        }

        return entity;
    }
}