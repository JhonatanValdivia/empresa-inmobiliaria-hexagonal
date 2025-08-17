package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoExpediente;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoExpediente;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Expediente {

    private Long idExpediente;
    private TipoExpediente tipoExpediente;;
    private EstadoExpediente estadoExpediente;
    private String observaciones;
    private Fecha fecha;
    private List<Plano> planos = new ArrayList<>();
    private PropiedadInmobiliaria propiedad;

    public Expediente() {
    }

    public Expediente(Long idExpediente, TipoExpediente tipoExpediente, EstadoExpediente estadoExpediente, String observaciones, Fecha fecha, List<Plano> planos, PropiedadInmobiliaria propiedad) {
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

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public PropiedadInmobiliaria getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadInmobiliaria propiedad) {
        this.propiedad = propiedad;
    }
}
