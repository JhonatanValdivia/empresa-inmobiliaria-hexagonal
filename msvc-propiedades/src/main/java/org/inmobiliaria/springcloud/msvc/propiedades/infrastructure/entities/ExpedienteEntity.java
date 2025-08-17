package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoExpediente;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoExpediente;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.FechaInfr;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "expedientes")
public class ExpedienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Long idExpediente;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoExpediente tipoExpediente;;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoExpediente estadoExpediente;

    @Column(name = "observaciones")
    private String observaciones;

    @Embedded
    private FechaInfr fecha;

    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PlanoEntity> planos = new ArrayList<>();


    @OneToOne(mappedBy = "expediente")
    @JsonBackReference
    private PropiedadInmobiliariaEntity propiedad;

    public ExpedienteEntity() {
    }

    public ExpedienteEntity(Long idExpediente, TipoExpediente tipoExpediente, EstadoExpediente estadoExpediente, String observaciones, FechaInfr fecha, List<PlanoEntity> planos, PropiedadInmobiliariaEntity propiedad) {
        this.idExpediente = idExpediente;
        this.tipoExpediente = tipoExpediente;
        this.estadoExpediente = estadoExpediente;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.planos = planos;
        this.propiedad = propiedad;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public TipoExpediente getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(TipoExpediente tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public EstadoExpediente getEstadoExpediente() {
        return estadoExpediente;
    }

    public void setEstadoExpediente(EstadoExpediente estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public FechaInfr getFecha() {
        return fecha;
    }

    public void setFecha(FechaInfr fecha) {
        this.fecha = fecha;
    }

    public List<PlanoEntity> getPlanos() {
        return planos;
    }

    public void setPlanos(List<PlanoEntity> planos) {
        this.planos = planos;
    }

    public PropiedadInmobiliariaEntity getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadInmobiliariaEntity propiedad) {
        this.propiedad = propiedad;
    }
}
