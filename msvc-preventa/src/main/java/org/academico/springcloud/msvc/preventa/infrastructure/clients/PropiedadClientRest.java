package org.academico.springcloud.msvc.preventa.infrastructure.clients;

import org.academico.springcloud.msvc.preventa.infrastructure.entities.PropiedadInmobiliariaPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-propiedades", url = "localhost:8010/api/propiedades")
public interface PropiedadClientRest {

    @GetMapping("/{id}")
    PropiedadInmobiliariaPojo obtenerPropiedad(@PathVariable Long id);
}