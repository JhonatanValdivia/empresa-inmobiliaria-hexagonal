package org.academico.springcloud.msvc.preventa.infrastructure.adapters;

import org.academico.springcloud.msvc.preventa.domain.models.Usuario;
import org.academico.springcloud.msvc.preventa.domain.ports.out.UsuarioPort;
import org.academico.springcloud.msvc.preventa.infrastructure.clients.UsuarioClientRest;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.UsuarioPojo;
import org.springframework.stereotype.Component;

@Component
public class ExternalUsuarioAdapter implements UsuarioPort {
    private final UsuarioClientRest usuarioClientRest;

    public ExternalUsuarioAdapter(UsuarioClientRest usuarioClientRest) {
        this.usuarioClientRest = usuarioClientRest;
    }


    private Usuario mapToDomain(UsuarioPojo pojo) {
        return new Usuario(
                pojo.getId(),
                pojo.getNombreCompleto() != null ? pojo.getNombreCompleto().getPrimerNombre() : null,
                pojo.getNombreCompleto() != null ? pojo.getNombreCompleto().getSegundoNombre() : null,
                pojo.getNombreCompleto() != null ? pojo.getNombreCompleto().getPrimerApellido() : null,
                pojo.getNombreCompleto() != null ? pojo.getNombreCompleto().getSegundoApellido() : null,
                pojo.getTelefono() != null ? pojo.getTelefono().getNumero() : null,
                pojo.getTelefono() != null ? pojo.getTelefono().getCodigoPais() : null,
                pojo.getCorreoElectronico() != null ? pojo.getCorreoElectronico().getValorCorreo() : null,
                pojo.getCorreoElectronico() != null ? pojo.getCorreoElectronico().getDominio() : null,
                pojo.getDireccion() != null ? pojo.getDireccion().getUbigeo() : null,
                pojo.getDireccion() != null ? pojo.getDireccion().getCiudad() : null,
                pojo.getDireccion() != null ? pojo.getDireccion().getDireccion() : null,
                pojo.getTipoUsuario() != null ? Usuario.TipoUsuario.valueOf(pojo.getTipoUsuario().name()) : null
        );
    }

    @Override
    public Usuario obtenerUsuario(Long idUsuario) {
        UsuarioPojo pojo = usuarioClientRest.detalleUsuario(idUsuario);

        if (pojo == null) {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + idUsuario);
        }

        // Mapear de UsuarioPojo a Usuario (dominio)
        return mapToDomain(pojo);
    }
}
