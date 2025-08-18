package org.academico.springcloud.msvc.comision.infrastructure.controllers;

import org.academico.springcloud.msvc.comision.application.services.ComisionServicio;
import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comisiones")
public class ComisionController {

    private final ComisionServicio comisionServicio;

    public ComisionController(ComisionServicio comisionServicio) {
        this.comisionServicio = comisionServicio;
    }
    @PostMapping
    public ResponseEntity<Comision> crearComision(@RequestBody Comision comision) {
        Comision crearComision = comisionServicio.crearComision(comision);
        return new ResponseEntity<>(crearComision, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comision> porIdComision(@PathVariable Long id) {
        return comisionServicio.comisionPorId(id)
                .map(comision -> new ResponseEntity<>(comision, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Comision>> getTodasComisiones() {
        List<Comision> comisiones = comisionServicio.obtenerTodasComisiones();
        return new ResponseEntity<>(comisiones, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comision> actualizarComision(@PathVariable Long id, @RequestBody Comision comisionActualizada) {
        return comisionServicio.actualizarComision(id, comisionActualizada)
                .map(comision-> new ResponseEntity<>(comision, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComisionId(@PathVariable Long id) {
        if (comisionServicio.eliminarComision(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
