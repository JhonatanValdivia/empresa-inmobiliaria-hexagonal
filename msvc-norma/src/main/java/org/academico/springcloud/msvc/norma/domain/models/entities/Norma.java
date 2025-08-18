package org.academico.springcloud.msvc.norma.domain.models.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.norma.domain.models.enums.TipoNorma;
import org.academico.springcloud.msvc.norma.domain.models.valueobjects.Fecha;


public class Norma {

    private Long idNorma;
    private Fecha fecha;
    private TipoNorma tipo;
    private String estadoNorma;
    private String descripcion;
    public Norma() {}
    public Norma(Fecha fecha, TipoNorma tipo, String estadoNorma, String descripcion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.estadoNorma = estadoNorma;
        this.descripcion = descripcion;
    }

    public Long getIdNorma() {
        return idNorma;
    }

    public void setIdNorma(Long idNorma) {
        this.idNorma = idNorma;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public TipoNorma getTipo() {
        return tipo;
    }

    public void setTipo(TipoNorma tipo) {
        this.tipo = tipo;
    }

    public String getEstadoNorma() {
        return estadoNorma;
    }

    public void setEstadoNorma(String estadoNorma) {
        this.estadoNorma = estadoNorma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}