package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.EstadoDocumentoLegal;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoDocumentoLegal;

@Entity
@Table(name = "documentos_legales")
public class DocumentoLegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_legal")
    private Long idDocumentoLegal;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoDocumentoLegal tipoDocumento;

    @Column(name = "nombre_notaria", nullable = false)
    private String nombreNotaria;

    @Column(name = "numero_inscripcion_sunarp", nullable = false)
    private String numeroInscripcionSunarp;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoDocumentoLegal estadoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonBackReference
    private PropiedadInmobiliariaEntity propiedad;

    public DocumentoLegalEntity() {
    }

    public DocumentoLegalEntity(Long idDocumentoLegal, TipoDocumentoLegal tipoDocumento, String nombreNotaria, String numeroInscripcionSunarp, EstadoDocumentoLegal estadoDocumento, PropiedadInmobiliariaEntity propiedad) {
        this.idDocumentoLegal = idDocumentoLegal;
        this.tipoDocumento = tipoDocumento;
        this.nombreNotaria = nombreNotaria;
        this.numeroInscripcionSunarp = numeroInscripcionSunarp;
        this.estadoDocumento = estadoDocumento;
        this.propiedad = propiedad;
    }

    public Long getIdDocumentoLegal() {
        return idDocumentoLegal;
    }

    public void setIdDocumentoLegal(Long idDocumentoLegal) {
        this.idDocumentoLegal = idDocumentoLegal;
    }

    public TipoDocumentoLegal getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoLegal tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombreNotaria() {
        return nombreNotaria;
    }

    public void setNombreNotaria(String nombreNotaria) {
        this.nombreNotaria = nombreNotaria;
    }

    public String getNumeroInscripcionSunarp() {
        return numeroInscripcionSunarp;
    }

    public void setNumeroInscripcionSunarp(String numeroInscripcionSunarp) {
        this.numeroInscripcionSunarp = numeroInscripcionSunarp;
    }

    public EstadoDocumentoLegal getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(EstadoDocumentoLegal estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public PropiedadInmobiliariaEntity getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadInmobiliariaEntity propiedad) {
        this.propiedad = propiedad;
    }
}
