package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters;


import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.pojos.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuario", url="http://localhost:8040")
public interface UsuarioClientRest {
    @GetMapping("/api/usuarios/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/api/usuarios")
    Usuario crear(@RequestBody Usuario usuario);
}
