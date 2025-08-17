package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoServicio;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoServicio;

@Entity
@Table(name = "servicios")
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoServicio tipoServicio;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoServicio estadoServicio;

    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Column(name = "numero_suministro", nullable = false)
    private String numeroSuministro;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonBackReference
    private PropiedadInmobiliariaEntity propiedad;


    public ServicioEntity() {
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public EstadoServicio getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(EstadoServicio estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNumeroSuministro() {
        return numeroSuministro;
    }

    public void setNumeroSuministro(String numeroSuministro) {
        this.numeroSuministro = numeroSuministro;
    }

    public PropiedadInmobiliariaEntity getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadInmobiliariaEntity propiedad) {
        this.propiedad = propiedad;
    }
}
