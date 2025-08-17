package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoServicio;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoServicio;

public class Servicio {

    private Long idServicio;
    private TipoServicio tipoServicio;
    private EstadoServicio estadoServicio;
    private String proveedor;
    private String numeroSuministro;
    private PropiedadInmobiliaria propiedad;

    public Servicio() {
    }

    public Servicio(Long idServicio, TipoServicio tipoServicio, EstadoServicio estadoServicio, String proveedor, String numeroSuministro, PropiedadInmobiliaria propiedad) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.estadoServicio = estadoServicio;
        this.proveedor = proveedor;
        this.numeroSuministro = numeroSuministro;
        this.propiedad = propiedad;
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

    public PropiedadInmobiliaria getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadInmobiliaria propiedad) {
        this.propiedad = propiedad;
    }
}
