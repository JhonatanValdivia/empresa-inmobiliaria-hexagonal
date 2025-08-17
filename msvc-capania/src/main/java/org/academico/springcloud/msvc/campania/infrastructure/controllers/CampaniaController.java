package org.academico.springcloud.msvc.campania.infrastructure.controllers;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;
import org.academico.springcloud.msvc.campania.domain.models.entities.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.domain.ports.in.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/campania")
public class CampaniaController {

    private final CrearCampaniaUseCase createUseCase;
    private final ActualizarCampaniaUseCase updateUseCase;
    private final EliminarCampaniaUseCase deleteUseCase;
    private final AprobarMontoUseCase aprobarMontoUseCase;
    private final GestionarProveedorUseCase gestionarProveedorUseCase;
    private final ConsultarCampaniaUseCase consultarCampaniaUseCase;

    public CampaniaController(
            @Qualifier("crearCampaniaUseCaselmpl") CrearCampaniaUseCase createUseCase,
            ConsultarCampaniaUseCase consultarCampaniaUseCase,
            @Qualifier("actualizarCampaniaUseCaseImpl") ActualizarCampaniaUseCase updateUseCase,
            @Qualifier("eliminarCampaniaUseCaseImpl") EliminarCampaniaUseCase deleteUseCase,
            @Qualifier("aprobarMontoUseCaseImpl") AprobarMontoUseCase aprobarMontoUseCase,
            @Qualifier("gestionarProveedorUseCaseImpl") GestionarProveedorUseCase gestionarProveedorUseCase
    ) {
        this.createUseCase = createUseCase;
        this.consultarCampaniaUseCase = consultarCampaniaUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.aprobarMontoUseCase = aprobarMontoUseCase;
        this.gestionarProveedorUseCase = gestionarProveedorUseCase;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Campania> campanias = consultarCampaniaUseCase.listar();
        if (campanias.isEmpty()) {
            return ResponseEntity.status(204).body("No hay campañas registradas.");
        }
        return ResponseEntity.ok(campanias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        return consultarCampaniaUseCase.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404)
                        .body("La campaña con ID " + id + " no existe."));
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Campania campania) {
        // Validar proveedores
        if (campania.getProveedores() == null || campania.getProveedores().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe proporcionar al menos un proveedor para crear la campaña.");
        }
        for (ProveedorPublicidad proveedor : campania.getProveedores()) {
            if (proveedor.getIdProveedor() == null) {
                return ResponseEntity.badRequest().body("El proveedor debe tener un ID válido.");
            }
        }

        // Validar monto
        if (campania.getMonto() == null || campania.getMonto().compareTo(BigDecimal.valueOf(5)) < 0) {
            return ResponseEntity.badRequest().body("El monto debe ser al menos 5.");
        }

        try {
            Campania nuevaCampania = createUseCase.create(campania);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCampania);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear la campaña: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/aprobar-monto")
    public ResponseEntity<?> aprobarMonto(@PathVariable Long id, @RequestBody MontoRequerido request) {
        try {
            BigDecimal nuevoMonto = request.getMonto();
            if (nuevoMonto == null || nuevoMonto.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("El monto debe ser mayor que cero.");
            }
            Campania campania = aprobarMontoUseCase.approve(id, nuevoMonto);
            return ResponseEntity.ok(campania);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            deleteUseCase.delete(id);
            return ResponseEntity.ok("Campaña eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{idCampania}/proveedor/{idProveedor}")
    public ResponseEntity<?> editarProveedor(@PathVariable Long idCampania,
                                             @PathVariable Long idProveedor,
                                             @RequestBody Map<String, String> body) {
        try {
            Campania campania = gestionarProveedorUseCase.editProveedor(
                    idCampania,
                    idProveedor,
                    body.getOrDefault("nombre", null),
                    body.getOrDefault("cuentaPublicitaria", null)
            );
            return ResponseEntity.ok(campania);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idCampania}/proveedor/{idProveedor}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long idCampania, @PathVariable Long idProveedor) {
        try {
            gestionarProveedorUseCase.deleteProveedor(idCampania, idProveedor);
            return ResponseEntity.ok("Proveedor eliminado exitosamente");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
