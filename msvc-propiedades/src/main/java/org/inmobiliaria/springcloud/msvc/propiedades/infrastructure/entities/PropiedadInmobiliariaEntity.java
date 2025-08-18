package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoPropiedad;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoPropiedad;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.PrecioInfr;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.UbicacionInfr;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.ZonificacionInfr;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "propiedades_inmobiliarias")
public class PropiedadInmobiliariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Long idPropiedad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedad", nullable = false)
    private TipoPropiedad tipoPropiedad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPropiedad estado;

    @Embedded
    private PrecioInfr precio;

    @Embedded
    private UbicacionInfr ubicacion;

    @Embedded
    private ZonificacionInfr zonificacion;

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocumentoLegalEntity> documentosLegales = new ArrayList<>();

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ServicioEntity> servicios = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_expediente")
    @JsonManagedReference
    private ExpedienteEntity expediente;
    @Column(name = "usuario_id", unique = true)   // 1-1 opcional
    private Long usuarioId;

    @Transient
    private String usuarioNombre;

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public PropiedadInmobiliariaEntity(Long idPropiedad, TipoPropiedad tipoPropiedad, EstadoPropiedad estado, PrecioInfr precio, UbicacionInfr ubicacion, ZonificacionInfr zonificacion, List<DocumentoLegalEntity> documentosLegales, List<ServicioEntity> servicios, ExpedienteEntity expediente) {
        this.idPropiedad = idPropiedad;
        this.tipoPropiedad = tipoPropiedad;
        this.estado = estado;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.zonificacion = zonificacion;
        this.documentosLegales = documentosLegales;
        this.servicios = servicios;
        this.expediente = expediente;
    }

    public PropiedadInmobiliariaEntity() {
    }


    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public PrecioInfr getPrecio() {
        return precio;
    }

    public void setPrecio(PrecioInfr precio) {
        this.precio = precio;
    }

    public UbicacionInfr getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionInfr ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ZonificacionInfr getZonificacion() {
        return zonificacion;
    }

    public void setZonificacion(ZonificacionInfr zonificacion) {
        this.zonificacion = zonificacion;
    }

    public List<DocumentoLegalEntity> getDocumentosLegales() {
        return documentosLegales;
    }

    public void setDocumentosLegales(List<DocumentoLegalEntity> documentosLegales) {
        this.documentosLegales = documentosLegales;
    }

    public List<ServicioEntity> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioEntity> servicios) {
        this.servicios = servicios;
    }

    public ExpedienteEntity getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteEntity expediente) {
        this.expediente = expediente;
    }
}
