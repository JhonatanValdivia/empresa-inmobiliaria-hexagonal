package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import jakarta.persistence.Transient;
import org.academico.springcloud.msvc.preventa.domain.models.PropiedadInmobiliaria;
import org.academico.springcloud.msvc.preventa.domain.models.Usuario;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PropiedadInmobiliariaPojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Preventa {
    private Long id;
    private LocalDate fechaInicio;
    private EstadoPreventa estado;
    private ContratoVenta contratoVenta;
    private List<PropuestaPago> propuestasPago;
    private List<VisitaProgramada> visitasProgramadas;
    private Long usuarioAgenteId;
    private Long usuarioClienteId;
    private Long idPropiedad;

    @Transient
    private PropiedadInmobiliaria propiedad;

    @Transient
    private Usuario agente;

    @Transient
    private Usuario cliente;

    public Preventa() {
        this.estado = EstadoPreventa.EN_EVALUACION;
        this.fechaInicio = null;
        this.contratoVenta = null;
        this.propuestasPago = new ArrayList<>();
        this.visitasProgramadas = new ArrayList<>();
        this.usuarioAgenteId = null;
        this.usuarioClienteId = null;
        this.idPropiedad = null;
    }

    // Constructor compatible con el mapeo actual del PreventaMapper (6 parámetros)
    // Constructor compatible con el mapeo actual del PreventaMapper
    public Preventa(Long id, LocalDate fechaInicio, EstadoPreventa estado, ContratoVenta contratoVenta,
                    List<PropuestaPago> propuestasPago, List<VisitaProgramada> visitasProgramadas) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.contratoVenta = contratoVenta;
        this.propuestasPago = (propuestasPago != null) ? propuestasPago : new ArrayList<>();
        this.visitasProgramadas = (visitasProgramadas != null) ? visitasProgramadas : new ArrayList<>();
    }

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria.");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser una fecha pasada.");
        }
        this.fechaInicio = fechaInicio;
    }

    public EstadoPreventa getEstado() { return estado; }
    public void setEstado(EstadoPreventa estado) { this.estado = estado; }
    public ContratoVenta getContratoVenta() { return contratoVenta; }
    public void setContratoVenta(ContratoVenta contratoVenta) { this.contratoVenta = contratoVenta; }
    public List<PropuestaPago> getPropuestasPago() { return new ArrayList<>(propuestasPago); }
    public void setPropuestasPago(List<PropuestaPago> propuestasPago) { this.propuestasPago = (propuestasPago != null) ? new ArrayList<>(propuestasPago) : new ArrayList<>(); }
    public List<VisitaProgramada> getVisitasProgramadas() { return new ArrayList<>(visitasProgramadas); }
    public void setVisitasProgramadas(List<VisitaProgramada> visitasProgramadas) { this.visitasProgramadas = (visitasProgramadas != null) ? new ArrayList<>(visitasProgramadas) : new ArrayList<>(); }
    public Long getUsuarioAgenteId() { return usuarioAgenteId; }
    public void setUsuarioAgenteId(Long usuarioAgenteId) { this.usuarioAgenteId = usuarioAgenteId; }
    public Long getUsuarioClienteId() { return usuarioClienteId; }
    public void setUsuarioClienteId(Long usuarioClienteId) { this.usuarioClienteId = usuarioClienteId; }
    public Long getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(Long idPropiedad) { this.idPropiedad = idPropiedad; }
    public PropiedadInmobiliaria getPropiedad() {return propiedad;}
    public void setPropiedad(PropiedadInmobiliaria propiedad) {this.propiedad = propiedad;}
    public Usuario getAgente() {return agente;}
    public void setAgente(Usuario agente) {this.agente = agente;}
    public Usuario getCliente() {return cliente;}
    public void setCliente(Usuario cliente) {this.cliente = cliente;}

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

    public void asociarUsuarios(Long idAgente, Long idCliente) {
        if (idAgente == null || idCliente == null) {
            throw new IllegalArgumentException("Los IDs no pueden ser nulos");
        }
        this.usuarioAgenteId = idAgente;
        this.usuarioClienteId = idCliente;
    }
}