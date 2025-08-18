package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.controllers;

import org.inmobiliaria.springcloud.msvc.propiedades.application.services.PropiedadService;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.pojos.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/propiedades")
public class PropiedadController {
    private final PropiedadService service;

    public PropiedadController(PropiedadService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<PropiedadInmobiliaria>> getAll() {
        List<PropiedadInmobiliaria> list = service.getAllPropiedades();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadInmobiliaria> getById(@PathVariable Long id) {
        Optional<PropiedadInmobiliaria> op = service.getPropiedad(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PropiedadInmobiliaria> create(@RequestBody PropiedadInmobiliaria propiedad) {
        PropiedadInmobiliaria created = service.createPropiedad(propiedad);
        URI uri = URI.create("/api/propiedades/" + created.getIdPropiedad());
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropiedadInmobiliaria> update(
            @PathVariable Long id,
            @RequestBody PropiedadInmobiliaria propiedad
    ) {
        propiedad.setIdPropiedad(id);
        return service.updatePropiedad(id,propiedad).map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePropiedad(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{propiedadId}/asignar-usuario/{usuarioId}")
    public ResponseEntity<Void> asignar(@PathVariable Long propiedadId, @PathVariable Long usuarioId) {
        service.asignarUsuario(propiedadId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{propiedadId}/eliminar-usuario")
    public ResponseEntity<Void> quitar(@PathVariable Long propiedadId) {
        service.eliminarUsuario(propiedadId);
        return ResponseEntity.noContent().build();
    }
    //para mostraar el detalle de usuario
    @GetMapping("/{propiedadId}/usuario-detalle")
    public ResponseEntity<UsuarioDetails> getUsuario(@PathVariable Long propiedadId) {
        var propiedad = service.getPropiedad(propiedadId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propiedad no existe"));

        if (propiedad.getUsuarioId() == null) {
            return ResponseEntity.noContent().build(); // la propiedad no tiene usuario asignado
        }

        return service.getUsuarioDetails(propiedad.getUsuarioId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
