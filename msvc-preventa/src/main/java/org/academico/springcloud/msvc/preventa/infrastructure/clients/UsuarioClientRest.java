package org.academico.springcloud.msvc.preventa.infrastructure.clients;

import org.academico.springcloud.msvc.preventa.infrastructure.entities.UsuarioPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-preventa", url="localhost:8040/api/usuarios")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    UsuarioPojo detalleUsuario(@PathVariable Long id);
}