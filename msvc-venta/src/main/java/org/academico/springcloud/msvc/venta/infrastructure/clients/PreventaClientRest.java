package org.academico.springcloud.msvc.venta.infrastructure.clients;

import org.academico.springcloud.msvc.venta.infrastructure.entities.PreventaPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;


@FeignClient(name = "msvc-preventa", url = "http://localhost:8081/api/preventas")
public interface PreventaClientRest {


    @GetMapping("/{id}")
    Optional<PreventaPojo> obtenerPreventa(@PathVariable Long id);
}