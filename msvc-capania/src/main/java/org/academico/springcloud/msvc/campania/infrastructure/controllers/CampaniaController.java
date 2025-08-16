package org.academico.springcloud.msvc.campania.infrastructure.controllers;

import feign.FeignException;
import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.model.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.domain.ports.in.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/campania")
public class CampaniaController {
    private final CreateCampaniaUseCase createUseCase;
    private final RetrieveCampaniaUseCase retrieveUseCase;
    private final UpdateCampaniaUseCase updateUseCase;
    private final DeleteCampaniaUseCase deleteUseCase;
    private final ApproveMontoUseCase approveMontoUseCase;
    private final ManageProveedorUseCase manageProveedorUseCase;

    public CampaniaController(CreateCampaniaUseCase createUseCase,
                              @Qualifier("retrieveCampaniaUseCaseImpl") RetrieveCampaniaUseCase retrieveUseCase,
                              @Qualifier("updateCampaniaUseCaseImpl") UpdateCampaniaUseCase updateUseCase,
                              @Qualifier("deleteCampaniaUseCaseImpl") DeleteCampaniaUseCase deleteUseCase,
                              ApproveMontoUseCase approveMontoUseCase,
                              ManageProveedorUseCase manageProveedorUseCase) {
        this.createUseCase = createUseCase;
        this.retrieveUseCase = retrieveUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.approveMontoUseCase = approveMontoUseCase;
        this.manageProveedorUseCase = manageProveedorUseCase;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Campania> campanias = retrieveUseCase.findAll();
        if (campanias.isEmpty()) {
            return ResponseEntity.status(204).body("No hay campañas registradas.");
        }
        return ResponseEntity.ok(campanias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        try {
            Optional<Campania> campaniaOpt = retrieveUseCase.findById(id);
            if (campaniaOpt.isPresent()) {
                return ResponseEntity.ok(campaniaOpt.get());
            } else {
                return ResponseEntity.status(404).body("La campaña con ID " + id + " no existe.");
            }
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje", "Error al conectar con msvc-propiedades: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Campania campania) {
        // Validar proveedores
        if (campania.getProveedores() == null || campania.getProveedores().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe proporcionar al menos un proveedor para crear la campaña.");
        }
        for (ProveedorPublicidad proveedor : campania.getProveedores()) {
            if (proveedor.getIdProveedor() == null) {
                try {
                    proveedor.agregarProveedor();
                } catch (IllegalStateException e) {
                    return ResponseEntity.badRequest().body("Error al agregar proveedor: " + e.getMessage());
                }
            } else {
                boolean proveedorExiste = retrieveUseCase.findAll().stream()
                        .flatMap(c -> c.getProveedores().stream())
                        .anyMatch(p -> p.getIdProveedor() != null && p.getIdProveedor().equals(proveedor.getIdProveedor()));
                if (!proveedorExiste) {
                    return ResponseEntity.badRequest().body("El proveedor con ID " + proveedor.getIdProveedor() + " no existe.");
                }
            }
        }
        // Validar monto
        if (campania.getMonto() == null) {
            return ResponseEntity.badRequest().body("El monto es obligatorio para crear la campaña.");
        }
        if (campania.getMonto().compareTo(BigDecimal.valueOf(5)) < 0) {
            return ResponseEntity.badRequest().body("El monto debe ser al menos 5.");
        }
        // Validar propiedad
        if (campania.getIdPropiedad() != null) {
            try {
                Optional<Campania> existingCampania = retrieveUseCase.findById(campania.getIdPropiedad());
                if (existingCampania.isEmpty()) {
                    return ResponseEntity.status(404).body("La propiedad con ID " + campania.getIdPropiedad() + " no existe.");
                }
            } catch (FeignException e) {
                if (e.status() == 404) {
                    return ResponseEntity.status(404).body("La propiedad con ID " + campania.getIdPropiedad() + " no existe.");
                } else {
                    return ResponseEntity.status(503).body("Error de conexión con msvc-propiedades: " + e.getMessage());
                }
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error interno al validar la propiedad: " + e.getMessage());
            }
        }
        try {
            Campania nuevaCampania = createUseCase.create(campania);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCampania);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error al crear la campaña: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/aprobar-monto")
    public ResponseEntity<?> aprobarMonto(@PathVariable Long id, @RequestBody MontoRequerido request) {
        Optional<Campania> campaniaOpt = retrieveUseCase.findById(id);
        if (campaniaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("La campaña con ID " + id + " no existe.");
        }
        try {
            BigDecimal nuevoMonto = request.getMonto();
            if (nuevoMonto == null || nuevoMonto.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("El monto debe ser mayor que cero.");
            }
            Campania campania = approveMontoUseCase.approve(id, nuevoMonto);
            return ResponseEntity.ok(campania);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Campania> campaniaOpt = retrieveUseCase.findById(id);
        if (campaniaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("La campaña con ID " + id + " no existe.");
        }
        deleteUseCase.delete(id);
        return ResponseEntity.ok("Campaña eliminada exitosamente.");
    }

    @PutMapping("/{idCampania}/proveedor/{idProveedor}")
    public ResponseEntity<?> editarProveedor(@PathVariable Long idCampania, @PathVariable Long idProveedor, @RequestBody Map<String, String> body) {
        Optional<Campania> campaniaOpt = retrieveUseCase.findById(idCampania);
        if (campaniaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Campaña no encontrada");
        }
        try {
            Campania campania = manageProveedorUseCase.editProveedor(idCampania, idProveedor, body.getOrDefault("nombre", null), body.getOrDefault("cuentaPublicitaria", null));
            return ResponseEntity.ok(campania);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idCampania}/proveedor/{idProveedor}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long idCampania, @PathVariable Long idProveedor) {
        Optional<Campania> campaniaOpt = retrieveUseCase.findById(idCampania);
        if (campaniaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Campaña no encontrada");
        }
        if (campaniaOpt.get().getProveedores().size() <= 1) {
            return ResponseEntity.badRequest().body("No se puede eliminar el último proveedor de la campaña");
        }
        try {
            manageProveedorUseCase.deleteProveedor(idCampania, idProveedor);
            return ResponseEntity.ok("Proveedor eliminado exitosamente");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}