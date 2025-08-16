package org.academico.springcloud.msvc.campania.infrastructure.clients;

import org.academico.springcloud.msvc.campania.domain.model.Propiedad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-propiedades", url = "http://localhost:8010/api/propiedades")
public interface PropiedadClientRest {
    @GetMapping("/detalle/{id}")
    Propiedad detalle(@PathVariable Long id);
}
