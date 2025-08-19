package org.academico.springcloud.msvc.venta.infrastructure.controllers;

import org.academico.springcloud.msvc.venta.application.services.VentaService;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
// import jakarta.servlet.http.HttpServletRequest; // Mantener si es necesario para otros métodos
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map; // Para recibir el ID de preventa
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // --- Endpoints CRUD existentes ---

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        List<Venta> ventas = ventaService.getAllVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // 204 No Content
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> detalleVenta(@PathVariable Long id) {
        return ventaService.getVentaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // 404 Not Found
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Venta venta) {
        try {
            // Las validaciones de los campos obligatorios se esperan del constructor de Venta del dominio.
            Venta ventaCreada = ventaService.createVenta(venta);
            URI location = UriComponentsBuilder.fromPath("/api/ventas/{id}")
                    .buildAndExpand(ventaCreada.getId())
                    .toUri();
            return ResponseEntity.created(location).body(ventaCreada); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la venta: " + e.getMessage()); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> editar(@PathVariable Long id, @RequestBody Venta venta) {
        try {
            // Asegura que el ID del path se use para la actualización
            venta.setId(id);
            Optional<Venta> ventaActualizada = ventaService.updateVenta(id, venta);
            return ventaActualizada
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // 404 Not Found si no existe
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = ventaService.deleteVenta(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarVentas() {
        return ResponseEntity.ok(ventaService.countAllVentas());
    }

    // --- Endpoints para DetalleVenta (relación de composición) ---

    @PostMapping("/{ventaId}/detalles")
    public ResponseEntity<Venta> agregarDetalle(@PathVariable Long ventaId, @RequestBody DetalleVenta detalleVenta) {
        try {
            Optional<Venta> ventaActualizada = ventaService.addDetalleVenta(ventaId, detalleVenta);
            return ventaActualizada
                    .map(ventaDB -> ResponseEntity.status(HttpStatus.CREATED).body(ventaDB)) // 201 Created con cuerpo
                    .orElse(ResponseEntity.notFound().build()); // 404 Not Found
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body(null); // 400 Bad Request
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{ventaId}/detalles/{detalleId}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long ventaId, @PathVariable Long detalleId) {
        try {
            boolean eliminado = ventaService.removeDetalleVenta(ventaId, detalleId);
            if (eliminado) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body(null); // 400 Bad Request
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }


    // NUEVO ENDPOINT: Asignar Preventa a Venta
    @PutMapping("/{idVenta}/asignar-preventa")
    public ResponseEntity<?> asignarPreventa(@PathVariable Long idVenta,
                                             @RequestBody Map<String, Long> request) {
        Long idPreventa = request.get("idPreventa");
        if (idPreventa == null) {
            return ResponseEntity.badRequest().body("El ID de preventa es obligatorio.");
        }

        try {
            Optional<Venta> ventaActualizada = ventaService.asignarPreventaAVenta(idVenta, idPreventa);
            return ventaActualizada
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Venta no encontrada
        } catch (RuntimeException e) {
            // Captura excepciones de negocio lanzadas desde el caso de uso
            // Se usa getCause() porque el use case envuelve las excepciones de dominio en RuntimeException
            if (e.getCause() instanceof IllegalStateException || e.getCause() instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body(e.getCause().getMessage()); // 400 Bad Request con mensaje específico
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al asignar preventa: " + e.getMessage());
        }
    }
}