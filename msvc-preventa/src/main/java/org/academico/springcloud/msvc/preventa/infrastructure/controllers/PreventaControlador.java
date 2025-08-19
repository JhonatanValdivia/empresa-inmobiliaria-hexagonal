package org.academico.springcloud.msvc.preventa.infrastructure.controllers;

import org.academico.springcloud.msvc.preventa.application.services.ContratoService;
import org.academico.springcloud.msvc.preventa.application.services.PreventaService;
import org.academico.springcloud.msvc.preventa.application.services.PropuestaService;
import org.academico.springcloud.msvc.preventa.application.services.VisitaService;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.AdicionalPreventaInfo; // Para getAdditionalTaskInfo
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita; // Para actualizarEstadoVisita

import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.AsociarUsuariosPreventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preventas")
public class PreventaControlador {

    private final PreventaService preventaService;
    private final ContratoService contratoService;
    private final PropuestaService propuestaService;
    private final VisitaService visitaService;
    private final AsociarUsuariosPreventa asociarUsuariosPreventa;

    @Autowired
    public PreventaControlador(
            PreventaService preventaService,
            ContratoService contratoService,
            PropuestaService propuestaService,
            VisitaService visitaService, AsociarUsuariosPreventa asociarUsuariosPreventa) {
        this.preventaService = preventaService;
        this.contratoService = contratoService;
        this.propuestaService = propuestaService;
        this.visitaService = visitaService;
        this.asociarUsuariosPreventa = asociarUsuariosPreventa;
    }

    // --- Endpoints de Preventa ---
    @PostMapping
    public ResponseEntity<?> crearPreventa(@RequestBody Preventa preventa) { // <-- Se queda simple
        try {
            Preventa crearPreventa = preventaService.crearPreventa(preventa);
            URI location = UriComponentsBuilder.fromPath("/api/preventas/{id}")
                    .buildAndExpand(crearPreventa.getId())
                    .toUri();
            return ResponseEntity.created(location).body(crearPreventa);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porIdPreventa(@PathVariable Long id) {
        return preventaService.preventaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Preventa>> listar() {
        List<Preventa> preventas = preventaService.obtenerTodasPreventas();
        if (preventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(preventas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPreventa(@PathVariable Long id, @RequestBody Preventa preventaActualizada) {
        try {
            preventaActualizada.setId(id);
            return preventaService.actualizarPreventa(id, preventaActualizada)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPreventa(@PathVariable Long id) {
        try {
            preventaService.eliminarPreventa(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{preventaId}/aprobar")
    public ResponseEntity<?> aprobarPreventa(@PathVariable Long preventaId) {
        try {
            return preventaService.aprobarPreventa(preventaId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // --- Endpoints de Contrato ---

    @PostMapping("/{preventaId}/contrato")
    public ResponseEntity<?> agregarContrato(@PathVariable Long preventaId, @RequestBody ContratoVenta contrato) {
        try {
            return contratoService.agregarContratoVenta(preventaId, contrato)
                    .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                    .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada
        } catch (RuntimeException e) { // Captura IllegalStateException/IllegalArgumentException del caso de uso
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{preventaId}/contrato")
    public ResponseEntity<?> actualizarContrato(@PathVariable Long preventaId, @RequestBody ContratoVenta contrato) {
        try {
            return contratoService.actualizarContratoVenta(preventaId, contrato)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Preventa o contrato no encontrado
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{preventaId}/contrato")
    public ResponseEntity<?> eliminarContrato(@PathVariable Long preventaId) {
        try {
            return contratoService.eliminarContratoVenta(preventaId)
                    .map(p -> ResponseEntity.noContent().build()) // Retorna 204 si se eliminó
                    .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{preventaId}/contrato")
    public ResponseEntity<?> obtenerContrato(@PathVariable Long preventaId) {
        return contratoService.obtenerContratoPorPreventa(preventaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Contrato o Preventa no encontrada
    }

    @PutMapping("/{preventaId}/contrato/anular")
    public ResponseEntity<?> anularContrato(@PathVariable Long preventaId) {
        try {
            return contratoService.anularContratoPreventa(preventaId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Preventa o contrato no encontrado
        } catch (RuntimeException e) { // Captura IllegalStateException del dominio
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // --- Endpoints de Propuesta de Pago ---
    @PostMapping("/{preventaId}/propuestas")
    public ResponseEntity<?> agregarPropuesta(@PathVariable Long preventaId, @RequestBody PropuestaPago propuesta) {
        try {
            return propuestaService.agregarPropuestaPago(preventaId, propuesta)
                    .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                    .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{preventaId}/propuestas")
    public ResponseEntity<?> listarPropuestas(@PathVariable Long preventaId) {
        return propuestaService.listarPropuestasPagoPorPreventa(preventaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada o sin propuestas
    }

    @GetMapping("/{preventaId}/propuestas/{propuestaId}")
    public ResponseEntity<?> detallePropuestaPago(@PathVariable Long preventaId, @PathVariable Long propuestaId) {
        return propuestaService.porIdPropuestaPago(preventaId, propuestaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Propuesta o Preventa no encontrada
    }

    @PutMapping("/{preventaId}/propuestas/{propuestaId}")
    public ResponseEntity<?> editarPropuestaPago(@PathVariable Long preventaId, @PathVariable Long propuestaId, @RequestBody PropuestaPago propuesta) {
        try {
            propuesta.setId(propuestaId);
            return propuestaService.actualizarPropuestaPago(preventaId, propuestaId, propuesta)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Propuesta o Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{preventaId}/propuestas/{propuestaId}/aceptar")
    public ResponseEntity<?> aceptarPropuesta(@PathVariable Long preventaId, @PathVariable Long propuestaId) {
        try {
            return propuestaService.aceptarPropuestaPagoPreventa(preventaId, propuestaId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Propuesta o Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // --- Endpoints de Visitas Programadas ---
    @PostMapping("/{preventaId}/visitas")
    public ResponseEntity<?> agregarVisita(@PathVariable Long preventaId, @RequestBody VisitaProgramada visita) {
        try {
            return visitaService.agregarVisitaProgramada(preventaId, visita)
                    .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                    .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{preventaId}/visitas")
    public ResponseEntity<?> listarVisitasProgramadas(@PathVariable Long preventaId) {
        return visitaService.listarVisitasProgramadasPorPreventa(preventaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Preventa no encontrada o sin visitas
    }

    @PutMapping("/{preventaId}/visitas/{visitaId}")
    public ResponseEntity<?> editarVisitaProgramada(@PathVariable Long preventaId, @PathVariable Long visitaId, @RequestBody VisitaProgramada visita) {
        try {
            visita.setId(visitaId); // Asegura el ID de la visita con el path variable
            return visitaService.actualizarVisitaProgramada(preventaId, visitaId, visita)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Visita o Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{preventaId}/visitas/{visitaId}/estado")
    public ResponseEntity<?> actualizarEstadoVisita(@PathVariable Long preventaId, @PathVariable Long visitaId, @RequestBody EstadoVisita estado) {
        try {
            return visitaService.actualizarEstadoVisitaPreventa(preventaId, visitaId, estado)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Visita o Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{preventaId}/visitas/{visitaId}/reprogramar")
    public ResponseEntity<?> reprogramarVisita(@PathVariable Long preventaId, @PathVariable Long visitaId, @RequestBody LocalDate fecha) {
        try {
            return visitaService.reprogramarVisitaPreventa(preventaId, visitaId, fecha)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); // Visita o Preventa no encontrada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{idPreventa}/asociar-usuarios")
    public ResponseEntity<Preventa> asociarUsuarios(
            @PathVariable Long idPreventa,
            @RequestParam Long idAgente,
            @RequestParam Long idCliente) {

        System.out.println("ID Preventa: " + idPreventa);
        System.out.println("ID Agente: " + idAgente);
        System.out.println("ID Cliente: " + idCliente);

        Preventa preventa = preventaService.asociarUsuariosPreventa(idPreventa, idAgente, idCliente);
        System.out.println("Preventa después de asociar: " + preventa);

        return ResponseEntity.ok(preventa);
    }
}