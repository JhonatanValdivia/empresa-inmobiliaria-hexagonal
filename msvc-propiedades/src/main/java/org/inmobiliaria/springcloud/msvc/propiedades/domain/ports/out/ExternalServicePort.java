package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;

import java.util.Optional;

public interface ExternalServicePort {
    Optional<UsuarioDetails> getUsuarioDetails(Long usuarioId);
   // void vincularPropiedadAUsuario(Long usuarioId, Long propiedadId);  // comando remoto
    //void desvincularPropiedadDeUsuario(Long usuarioId, Long propiedadId);


}
