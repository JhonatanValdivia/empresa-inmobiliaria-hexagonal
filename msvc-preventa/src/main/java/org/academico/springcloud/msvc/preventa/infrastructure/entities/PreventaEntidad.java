package org.academico.springcloud.msvc.preventa.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference; // Para el lado "dueño" de la relación
import jakarta.persistence.*;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa; // Usamos el enum del dominio

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "preventas")
public class PreventaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    private EstadoPreventa estado; // Enum del dominio

    @OneToOne(mappedBy = "preventa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference // Lado dueño de la relación ContratoVenta
    private ContratoVentaEntidad contratoVenta;

    @OneToMany(mappedBy = "preventa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Lado dueño de la relación PropuestasPago
    private List<PropuestaPagoEntidad> propuestasPago = new ArrayList<>();

    @OneToMany(mappedBy = "preventa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Lado dueño de la relación VisitasProgramadas
    private List<VisitaProgramadaEntidad> visitasProgramadas = new ArrayList<>();

    //relación con Usuario(agente y cliente)
    @Column(name="agente_id")
    private Long usuarioAgenteId;

    @Column(name = "cliente_id")
    private Long usuarioClienteId;

    @Column(name = "propiedad_id")
    private Long idPropiedad;

    public PreventaEntidad() {}

    public PreventaEntidad(Long id, LocalDate fechaInicio, EstadoPreventa estado, ContratoVentaEntidad contratoVenta, List<PropuestaPagoEntidad> propuestasPago, List<VisitaProgramadaEntidad> visitasProgramadas) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.contratoVenta = contratoVenta;
        this.propuestasPago = (propuestasPago != null) ? new ArrayList<>(propuestasPago) : new ArrayList<>();
        this.visitasProgramadas = (visitasProgramadas != null) ? new ArrayList<>(visitasProgramadas) : new ArrayList<>();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public EstadoPreventa getEstado() { return estado; }
    public void setEstado(EstadoPreventa estado) { this.estado = estado; }
    public ContratoVentaEntidad getContratoVenta() { return contratoVenta; }
    public void setContratoVenta(ContratoVentaEntidad contratoVenta) { this.contratoVenta = contratoVenta; }
    public List<PropuestaPagoEntidad> getPropuestasPago() { return propuestasPago; }
    public void setPropuestasPago(List<PropuestaPagoEntidad> propuestasPago) { this.propuestasPago = propuestasPago; }
    public List<VisitaProgramadaEntidad> getVisitasProgramadas() { return visitasProgramadas; }
    public void setVisitasProgramadas(List<VisitaProgramadaEntidad> visitasProgramadas) { this.visitasProgramadas = visitasProgramadas; }
    public Long getUsuarioAgenteId() {return usuarioAgenteId;}
    public void setUsuarioAgenteId(Long usuarioAgenteId) {
        this.usuarioAgenteId = usuarioAgenteId;
    }
    public Long getUsuarioClienteId() {return usuarioClienteId;}
    public void setUsuarioClienteId(Long usuarioClienteId) {this.usuarioClienteId = usuarioClienteId;}
    public Long getIdPropiedad() {return idPropiedad;}
    public void setIdPropiedad(Long idPropiedad) {this.idPropiedad = idPropiedad;   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreventaEntidad that = (PreventaEntidad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}