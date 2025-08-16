package org.academico.springcloud.msvc.preventa.models;

import java.util.List;

public class PropiedadInmobiliaria {
    private Long idPropiedad;
    private TipoPropiedad tipoPropiedad;
    private EstadoPropiedad estado;
    private Precio precio;
    private Ubicacion ubicacion;
    private Zonificacion zonificacion;
    private List<DocumentoLegal> documentosLegales;
    private List<Servicio> servicios;
    private Expediente expediente;
    private List<PropiedadInmobiliariaNorma> propiedadNormas;
    private List<Norma> normas;

    // Getters y Setters
    public Long getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(Long idPropiedad) { this.idPropiedad = idPropiedad; }
    public TipoPropiedad getTipoPropiedad() { return tipoPropiedad; }
    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) { this.tipoPropiedad = tipoPropiedad; }
    public EstadoPropiedad getEstado() { return estado; }
    public void setEstado(EstadoPropiedad estado) { this.estado = estado; }
    public Precio getPrecio() { return precio; }
    public void setPrecio(Precio precio) { this.precio = precio; }
    public Ubicacion getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
    public Zonificacion getZonificacion() { return zonificacion; }
    public void setZonificacion(Zonificacion zonificacion) { this.zonificacion = zonificacion; }
    public List<DocumentoLegal> getDocumentosLegales() { return documentosLegales; }
    public void setDocumentosLegales(List<DocumentoLegal> documentosLegales) { this.documentosLegales = documentosLegales; }
    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
    public Expediente getExpediente() { return expediente; }
    public void setExpediente(Expediente expediente) { this.expediente = expediente; }
    public List<PropiedadInmobiliariaNorma> getPropiedadNormas() { return propiedadNormas; }
    public void setPropiedadNormas(List<PropiedadInmobiliariaNorma> propiedadNormas) { this.propiedadNormas = propiedadNormas; }
    public List<Norma> getNormas() { return normas; }
    public void setNormas(List<Norma> normas) { this.normas = normas; }

    // Clases estáticas para objetos de valor
    public class Fecha {
        private Integer dia;
        private Integer mes;
        private Integer anio;

        public Integer getDia() {return dia;}
        public void setDia(Integer dia) {this.dia = dia;}
        public Integer getMes() {return mes;}
        public void setMes(Integer mes) {this.mes = mes;}
        public Integer getAnio() {return anio;}
        public void setAnio(Integer anio) {this.anio = anio;}
    }

    public static class Precio {
        private Double monto;
        private String moneda;

        public Double getMonto() { return monto; }
        public void setMonto(Double monto) { this.monto = monto; }
        public String getMoneda() { return moneda; }
        public void setMoneda(String moneda) { this.moneda = moneda; }
    }

    public static class Ubicacion {
        private String ubigeo;
        private String ciudad;
        private String direccion;

        public String getUbigeo() { return ubigeo; }
        public void setUbigeo(String ubigeo) { this.ubigeo = ubigeo; }
        public String getCiudad() { return ciudad; }
        public void setCiudad(String ciudad) { this.ciudad = ciudad; }
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
    }

    public static class Zonificacion {
        private String tipoZona;
        private String descripcionNormativa;
        private String usoPermitido;

        public String getTipoZona() { return tipoZona; }
        public void setTipoZona(String tipoZona) { this.tipoZona = tipoZona; }
        public String getDescripcionNormativa() { return descripcionNormativa; }
        public void setDescripcionNormativa(String descripcionNormativa) { this.descripcionNormativa = descripcionNormativa; }
        public String getUsoPermitido() { return usoPermitido; }
        public void setUsoPermitido(String usoPermitido) { this.usoPermitido = usoPermitido; }
    }

    // Clases estáticas para entidades relacionadas
    public static class DocumentoLegal {
        private Long idDocumentoLegal;
        private TipoDocumentoLegal tipoDocumento;
        private String nombreNotaria;
        private String numeroInscripcionSunarp;
        private EstadoDocumentoLegal estadoDocumento;

        public Long getIdDocumentoLegal() { return idDocumentoLegal; }
        public void setIdDocumentoLegal(Long idDocumentoLegal) { this.idDocumentoLegal = idDocumentoLegal; }
        public TipoDocumentoLegal getTipoDocumento() { return tipoDocumento; }
        public void setTipoDocumento(TipoDocumentoLegal tipoDocumento) { this.tipoDocumento = tipoDocumento; }
        public String getNombreNotaria() { return nombreNotaria; }
        public void setNombreNotaria(String nombreNotaria) { this.nombreNotaria = nombreNotaria; }
        public String getNumeroInscripcionSunarp() { return numeroInscripcionSunarp; }
        public void setNumeroInscripcionSunarp(String numeroInscripcionSunarp) { this.numeroInscripcionSunarp = numeroInscripcionSunarp; }
        public EstadoDocumentoLegal getEstadoDocumento() { return estadoDocumento; }
        public void setEstadoDocumento(EstadoDocumentoLegal estadoDocumento) { this.estadoDocumento = estadoDocumento; }
    }

    public static class Expediente {
        private Long idExpediente;
        private TipoExpediente tipoExpediente;
        private EstadoExpediente estadoExpediente;
        private String observaciones;
        private Fecha fecha;
        private List<Plano> planos;

        public Long getIdExpediente() { return idExpediente; }
        public void setIdExpediente(Long idExpediente) { this.idExpediente = idExpediente; }
        public TipoExpediente getTipoExpediente() { return tipoExpediente; }
        public void setTipoExpediente(TipoExpediente tipoExpediente) { this.tipoExpediente = tipoExpediente; }
        public EstadoExpediente getEstadoExpediente() { return estadoExpediente; }
        public void setEstadoExpediente(EstadoExpediente estadoExpediente) { this.estadoExpediente = estadoExpediente; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
        public Fecha getFecha() { return fecha; }
        public void setFecha(Fecha fecha) { this.fecha = fecha; }
        public List<Plano> getPlanos() { return planos; }
        public void setPlanos(List<Plano> planos) { this.planos = planos; }
    }

    public static class Norma {
        private Long idNorma;
        private Fecha fecha;
        private TipoNorma tipo;
        private String estadoNorma;
        private String descripcion;

        public Long getIdNorma() { return idNorma; }
        public void setIdNorma(Long idNorma) { this.idNorma = idNorma; }
        public Fecha getFecha() { return fecha; }
        public void setFecha(Fecha fecha) { this.fecha = fecha; }
        public TipoNorma getTipo() { return tipo; }
        public void setTipo(TipoNorma tipo) { this.tipo = tipo; }
        public String getEstadoNorma() { return estadoNorma; }
        public void setEstadoNorma(String estadoNorma) { this.estadoNorma = estadoNorma; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    }

    public static class Plano {
        private Long idPlano;
        private TipoPlano tipoPlano;
        private String archivoRuta;
        private String nombreArchivo;
        private EstadoPlano estadoPlano;
        private String observaciones;
        private String autor;
        private String version;

        public Long getIdPlano() { return idPlano; }
        public void setIdPlano(Long idPlano) { this.idPlano = idPlano; }
        public TipoPlano getTipoPlano() { return tipoPlano; }
        public void setTipoPlano(TipoPlano tipoPlano) { this.tipoPlano = tipoPlano; }
        public String getArchivoRuta() { return archivoRuta; }
        public void setArchivoRuta(String archivoRuta) { this.archivoRuta = archivoRuta; }
        public String getNombreArchivo() { return nombreArchivo; }
        public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
        public EstadoPlano getEstadoPlano() { return estadoPlano; }
        public void setEstadoPlano(EstadoPlano estadoPlano) { this.estadoPlano = estadoPlano; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }
        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }
    }

    public static class PropiedadInmobiliariaNorma {
        private Long id;
        private Long normaId;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getNormaId() { return normaId; }
        public void setNormaId(Long normaId) { this.normaId = normaId; }
    }

    public static class Servicio {
        private Long idServicio;
        private TipoServicio tipoServicio;
        private EstadoServicio estadoServicio;
        private String proveedor;
        private String numeroSuministro;

        public Long getIdServicio() { return idServicio; }
        public void setIdServicio(Long idServicio) { this.idServicio = idServicio; }
        public TipoServicio getTipoServicio() { return tipoServicio; }
        public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }
        public EstadoServicio getEstadoServicio() { return estadoServicio; }
        public void setEstadoServicio(EstadoServicio estadoServicio) { this.estadoServicio = estadoServicio; }
        public String getProveedor() { return proveedor; }
        public void setProveedor(String proveedor) { this.proveedor = proveedor; }
        public String getNumeroSuministro() { return numeroSuministro; }
        public void setNumeroSuministro(String numeroSuministro) { this.numeroSuministro = numeroSuministro; }
    }

    // Enums como clases estáticas internas
    public static enum TipoServicio {
        ELECTRICIDAD,
        AGUA,
        GAS,
        INTERNET,
        DESAGUE,
        OTRO
    }

    public static enum TipoPropiedad {
        INTERNA,
        TERCERO
    }

    public static enum TipoPlano {
        ARQUITECTONICO,
        UBICACION,
        TOPOGRAFICO,
        ESTRUCTURAL
    }

    public static enum TipoNorma {
        SEGURIDAD,
        AMBIENTAL,
        CONSTRUCCION
    }

    public static enum TipoExpediente {
        HABILITACION_URBANA,
        INSCRIPCION_SUNARP,
        CONTRATO_COMPRAVENTA
    }

    public static enum TipoDocumentoLegal {
        ESCRITURA_PUBLICA,
        CERTIFICADO_POSESION,
        CONTRATO_COMPRAVENTA
    }

    public static enum EstadoPropiedad {
        DISPONIBLE,
        OCUPADO,
        EN_MANTENIMIENTO,
        VENDIDO
    }

    public static enum EstadoServicio {
        ACTIVO,
        INACTIVO,
        SUSPENDIDO,
        PENDIENTE
    }

    public static enum EstadoPlano {
        PENDIENTE,
        APROBADO,
        RECHAZADO,
        OBSERVADO
    }

    public static enum EstadoExpediente {
        PENDIENTE,
        EN_PROCESO,
        APROBADO,
        OBSERVADO,
        RECHAZADO
    }

    public static enum EstadoDocumentoLegal {
        VALIDO,
        OBSERVADO,
        VENCIDO,
        NO_PRESENTADO
    }
}