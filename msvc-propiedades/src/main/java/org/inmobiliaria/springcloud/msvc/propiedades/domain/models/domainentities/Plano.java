package org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoPlano;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoPlano;

public class Plano {

    private Long idPlano;
    private TipoPlano tipoPlano;
    private String archivoRuta;
    private String nombreArchivo;
    private EstadoPlano estadoPlano;
    private String observaciones;
    private String autor;
    private String version;
    private Expediente expediente;

    public Plano() {
    }

    public Plano(Long idPlano, TipoPlano tipoPlano, String archivoRuta, String nombreArchivo, EstadoPlano estadoPlano, String observaciones, String autor, String version, Expediente expediente) {
        this.idPlano = idPlano;
        this.tipoPlano = tipoPlano;
        this.archivoRuta = archivoRuta;
        this.nombreArchivo = nombreArchivo;
        this.estadoPlano = estadoPlano;
        this.observaciones = observaciones;
        this.autor = autor;
        this.version = version;
        this.expediente = expediente;
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }

    public TipoPlano getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public EstadoPlano getEstadoPlano() {
        return estadoPlano;
    }

    public void setEstadoPlano(EstadoPlano estadoPlano) {
        this.estadoPlano = estadoPlano;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}


