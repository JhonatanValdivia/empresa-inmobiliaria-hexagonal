package org.academico.springcloud.msvc.cobro.infrastructure.controllers;

import org.academico.springcloud.msvc.cobro.application.services.CobroService;
import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.FechaCobro;
import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.MontoCobro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cobro")
public class CobroController {

    private final CobroService cobroService;

    public CobroController(CobroService cobroService) {
        this.cobroService = cobroService;
    }

    @GetMapping
    public ResponseEntity<List<Cobro>> listarCobros() {
        List<Cobro> cobros = cobroService.getAllCobros();
        if (cobros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // 204 No Content
        }
        return ResponseEntity.ok(cobros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobro> obtenerCobroPorId(@PathVariable Long id) {
        return cobroService.getCobroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // 404 Not Found
    }

    @PostMapping
    public ResponseEntity<?> crearCobro(@RequestBody Cobro cobro) {
        // Validaciones específicas de la API o del contrato de la solicitud
        if (cobro.getIdVenta() == null || cobro.getFechaCobro() == null || cobro.getMontoCobro() == null) {
            return ResponseEntity.badRequest().body("Id de venta, fecha y monto son obligatorios.");
        }

        // Aquí puedes realizar validaciones adicionales de los value objects si no se hicieron en sus constructores
        // Por ejemplo, para FechaCobro y MontoCobro, sus constructores ya tienen validaciones
        try {
            // Intentar construir el Cobro para que las validaciones de dominio se apliquen
            // Si el cobro del RequestBody ya fue deserializado con exito, sus VOs ya pasaron la validacion
            // Pero si se pasan objetos nulos que luego se setean, podemos tener un problema.
            // Es mejor validar aqui o tener un DTO para la entrada.
            Cobro createdCobro = cobroService.createCobro(cobro);
            URI location = UriComponentsBuilder.fromPath("/api/cobro/{id}")
                    .buildAndExpand(createdCobro.getIdCobro())
                    .toUri();
            return ResponseEntity.created(location).body(createdCobro); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Cualquier otra excepción no esperada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el cobro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cobro> actualizarCobro(@PathVariable Long id, @RequestBody Cobro cobro) {
        // Validación básica: la fecha y el monto no pueden ser nulos si se incluyen en la actualización
        if (cobro.getFechaCobro() == null || cobro.getMontoCobro() == null) {
            return ResponseEntity.badRequest().body(null); // O un mensaje de error más específico
        }

        try {
            return cobroService.updateCobro(id, cobro)
                    .map(updatedCobro -> ResponseEntity.ok(updatedCobro))
                    .orElse(ResponseEntity.notFound().build()); // 404 Not Found si no existe
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // O el mensaje de la excepción
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCobro(@PathVariable Long id) {
        boolean deleted = cobroService.deleteCobro(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PutMapping("/{id}/registrar-pago")
    public ResponseEntity<?> registrarPago(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double monto = request.get("monto");

        if (monto == null || monto <= 0) {
            return ResponseEntity.badRequest().body("Monto de pago inválido. Debe ser un número mayor que cero.");
        }

        try {
            BigDecimal montoPago = BigDecimal.valueOf(monto);
            Optional<Cobro> optionalCobro = cobroService.registrarPago(id, montoPago);

            // --- CORRECCIÓN AQUÍ ---
            return optionalCobro
                    .<ResponseEntity<?>>map(cobro -> ResponseEntity.ok(cobro)) // Explícitamente mapea a ResponseEntity<?>
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND) // Devuelve ResponseEntity<?> con String
                            .body("Cobro con ID " + id + " no existe."));
            // -----------------------

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al registrar el pago.");
        }
    }

    @GetMapping("/{id}/generar-comprobante")
    public ResponseEntity<?> generarComprobante(@PathVariable Long id) {
        try {
            Optional<String> comprobante = cobroService.generateComprobante(id);
            return comprobante
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cobro con ID " + id + " no existe."));
        } catch (RuntimeException e) { // Captura la RuntimeException de dominio
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al generar el comprobante.");
        }
    }
}