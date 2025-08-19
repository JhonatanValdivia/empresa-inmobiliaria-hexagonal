package org.academico.springcloud.msvc.preventa.infrastructure.adapters;

import feign.FeignException;
import org.academico.springcloud.msvc.preventa.domain.models.Usuario;
import org.academico.springcloud.msvc.preventa.domain.ports.out.UsuarioPort;
import org.academico.springcloud.msvc.preventa.infrastructure.clients.UsuarioClientRest;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.UsuarioPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExternalUsuarioAdapter implements UsuarioPort {

    private static final Logger log = LoggerFactory.getLogger(ExternalUsuarioAdapter.class);
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
    public Optional<Usuario> obtenerUsuario(Long idUsuario) {
        try {
            UsuarioPojo pojo = usuarioClientRest.detalleUsuario(idUsuario);
            return Optional.ofNullable(mapToDomain(pojo));
        } catch (FeignException.NotFound e) {
            log.info("No se encontró el usuario con id: {}. El servicio externo devolvió 404.", idUsuario);
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error inesperado al intentar obtener el usuario con id: {}. Causa: {}", idUsuario, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
