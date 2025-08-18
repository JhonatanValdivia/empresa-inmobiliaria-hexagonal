package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Preventa {
    private Long id;
    private LocalDate fechaInicio;
    private EstadoPreventa estado; // "EN_EVALUACION", "APROBADA", "CANCELADA", "FINALIZADA"

    private ContratoVenta contratoVenta;
    private List<PropuestaPago> propuestasPago;
    private List<VisitaProgramada> visitasProgramadas;

    private Long usuarioAgenteId;

    private Long usuarioClienteId;

    public Preventa() {
        this.estado = EstadoPreventa.EN_EVALUACION; // Estado inicial
        this.fechaInicio = LocalDate.now();
        this.contratoVenta = null;
        this.propuestasPago = new ArrayList<>();
        this.visitasProgramadas = new ArrayList<>();
    }

    public Preventa(Long id, LocalDate fechaInicio, EstadoPreventa estado, ContratoVenta contratoVenta, List<PropuestaPago> propuestasPago, List<VisitaProgramada> visitasProgramadas) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio de la preventa es obligatoria.");
        }
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.estado = (estado != null) ? estado : EstadoPreventa.EN_EVALUACION;
        this.contratoVenta = contratoVenta;
        this.propuestasPago = (propuestasPago != null) ? new ArrayList<>(propuestasPago) : new ArrayList<>();
        this.visitasProgramadas = (visitasProgramadas != null) ? new ArrayList<>(visitasProgramadas) : new ArrayList<>();
    }

    // Métodos de Lógica de Negocio (del agregado)
    public void aprobarPreventa() {
        if (this.estado != EstadoPreventa.EN_EVALUACION) {
            throw new IllegalStateException("La preventa solo puede ser aprobada si está EN_EVALUACION. Estado actual: " + this.estado);
        }
        this.estado = EstadoPreventa.APROBADA;
        System.out.println("Preventa " + this.id + " marcada como APROBADA.");
    }

    public void addPropuestaPago(PropuestaPago propuesta) {
        if (propuesta == null) {
            throw new IllegalArgumentException("La propuesta de pago no puede ser nula.");
        }
        if (propuesta.getId() != null && this.propuestasPago.stream().anyMatch(p -> p.getId().equals(propuesta.getId()))) {
            throw new IllegalArgumentException("La propuesta de pago con ID " + propuesta.getId() + " ya existe en esta preventa.");
        }
        this.propuestasPago.add(propuesta);
        // Ojo: Si la PropuestaPago tiene una referencia a Preventa, debe ser bidireccional en el dominio también.
        // Pero en este caso, se gestiona el agregado.
    }

    public Optional<PropuestaPago> findPropuestaPagoById(Long propuestaId) {
        return this.propuestasPago.stream()
                .filter(p -> Objects.equals(p.getId(), propuestaId))
                .findFirst();
    }

    public void addVisitaProgramada(VisitaProgramada visita) {
        if (visita == null) {
            throw new IllegalArgumentException("La visita programada no puede ser nula.");
        }
        if (visita.getId() != null && this.visitasProgramadas.stream().anyMatch(v -> v.getId().equals(visita.getId()))) {
            throw new IllegalArgumentException("La visita programada con ID " + visita.getId() + " ya existe en esta preventa.");
        }
        this.visitasProgramadas.add(visita);
    }

    public Optional<VisitaProgramada> findVisitaProgramadaById(Long visitaId) {
        return this.visitasProgramadas.stream()
                .filter(v -> Objects.equals(v.getId(), visitaId))
                .findFirst();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public EstadoPreventa getEstado() { return estado; }
    public void setEstado(EstadoPreventa estado) { this.estado = estado; }
    public ContratoVenta getContratoVenta() { return contratoVenta; }
    public void setContratoVenta(ContratoVenta contratoVenta) { this.contratoVenta = contratoVenta; }
    public List<PropuestaPago> getPropuestasPago() { return new ArrayList<>(propuestasPago); } // Devolver copia
    public void setPropuestasPago(List<PropuestaPago> propuestasPago) { this.propuestasPago = (propuestasPago != null) ? new ArrayList<>(propuestasPago) : new ArrayList<>(); }
    public List<VisitaProgramada> getVisitasProgramadas() { return new ArrayList<>(visitasProgramadas); } // Devolver copia
    public void setVisitasProgramadas(List<VisitaProgramada> visitasProgramadas) { this.visitasProgramadas = (visitasProgramadas != null) ? new ArrayList<>(visitasProgramadas) : new ArrayList<>(); }
    public Long getUsuarioAgenteId() {
        return usuarioAgenteId;
    }
    public void setUsuarioAgenteId(Long usuarioAgenteId) {
        this.usuarioAgenteId = usuarioAgenteId;
    }
    public Long getUsuarioClienteId() {
        return usuarioClienteId;
    }
    public void setUsuarioClienteId(Long usuarioClienteId) {
        this.usuarioClienteId = usuarioClienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preventa preventa = (Preventa) o;
        return Objects.equals(id, preventa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Método de dominio para asociar usuarios
    public void asociarUsuarios(Long idAgente, Long idCliente) {
        if(idAgente == null || idCliente == null) {
            throw new IllegalArgumentException("Los IDs no pueden ser nulos");
        }
        this.usuarioAgenteId = idAgente;
        this.usuarioClienteId = idCliente;
    }
}