package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoPropiedad;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoPropiedad;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Precio;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Ubicacion;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Zonificacion;

import java.util.ArrayList;
import java.util.List;

public class PropiedadInmobiliaria {
    private Long idPropiedad;
    private TipoPropiedad tipoPropiedad;
    private EstadoPropiedad estado;
    private Precio precio;
    private Ubicacion ubicacion;
    private Zonificacion zonificacion;
    private List<DocumentoLegal> documentosLegales = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();
    private Expediente expediente;

    public PropiedadInmobiliaria(){

    }

    public PropiedadInmobiliaria(Long idPropiedad, TipoPropiedad tipoPropiedad, EstadoPropiedad estado, Precio precio, Ubicacion ubicacion, Zonificacion zonificacion, List<DocumentoLegal> documentosLegales, List<Servicio> servicios, Expediente expediente) {
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

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Zonificacion getZonificacion() {
        return zonificacion;
    }

    public void setZonificacion(Zonificacion zonificacion) {
        this.zonificacion = zonificacion;
    }

    public List<DocumentoLegal> getDocumentosLegales() {
        return documentosLegales;
    }

    public void setDocumentosLegales(List<DocumentoLegal> documentosLegales) {
        this.documentosLegales = documentosLegales;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
