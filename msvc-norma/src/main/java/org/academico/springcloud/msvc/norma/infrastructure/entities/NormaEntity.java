package org.academico.springcloud.msvc.norma.infrastructure.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.norma.domain.models.enums.TipoNorma;
import org.academico.springcloud.msvc.norma.infrastructure.entities.valueobjects.FechaInfr;


@Entity
@Table(name = "normas")
public class NormaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNorma;

    @Embedded
    private FechaInfr fecha;

    @Enumerated(EnumType.STRING)
    private TipoNorma tipo;

    private String estadoNorma;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    public NormaEntity() {}

    public NormaEntity(Long idNorma, FechaInfr fecha, TipoNorma tipo, String estadoNorma, String descripcion) {
        this.idNorma = idNorma;
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

    public FechaInfr getFecha() {
        return fecha;
    }

    public void setFecha(FechaInfr fecha) {
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