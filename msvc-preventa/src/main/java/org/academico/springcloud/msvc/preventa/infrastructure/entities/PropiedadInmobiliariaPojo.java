package org.academico.springcloud.msvc.preventa.infrastructure.entities;

import java.math.BigDecimal;
import java.util.List;

public class PropiedadInmobiliariaPojo {
    private Long idPropiedad;
    private TipoPropiedad tipoPropiedad;
    private EstadoPropiedad estado;
    private Precio precio;
    private Ubicacion ubicacion;
    private Zonificacion zonificacion;
    private List<DocumentoLegalPojo> documentosLegales;
    private List<ServicioPojo> servicios;
    private ExpedientePojo expediente;

    // --- Clases Anidadas para Objetos Valor ---
    public static class Precio {
        private BigDecimal monto;
        private String moneda;

        public BigDecimal getMonto() { return monto; }
        public void setMonto(BigDecimal monto) { this.monto = monto; }
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

    // --- Clases Anidadas para Entidades Complejas ---
    public static class DocumentoLegalPojo {
        private Long idDocumentoLegal;
        private TipoDocumentoLegal tipoDocumento;
        private String nombreNotaria;
        private String numeroInscripcionSunarp;
        private EstadoDocumentoLegal estadoDocumento;
        // Getters y Setters
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

    public static class ServicioPojo {
        private Long idServicio;
        private TipoServicio tipoServicio;
        private EstadoServicio estadoServicio;
        private String proveedor;
        private String numeroSuministro;
        // Getters y Setters
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

    public static class ExpedientePojo {
        private Long idExpediente;
        private TipoExpediente tipoExpediente;
        private EstadoExpediente estadoExpediente;
        private String observaciones;
        private FechaPojo fecha;
        private List<PlanoPojo> planos;
        // Getters y Setters
        public Long getIdExpediente() { return idExpediente; }
        public void setIdExpediente(Long idExpediente) { this.idExpediente = idExpediente; }
        public TipoExpediente getTipoExpediente() { return tipoExpediente; }
        public void setTipoExpediente(TipoExpediente tipoExpediente) { this.tipoExpediente = tipoExpediente; }
        public EstadoExpediente getEstadoExpediente() { return estadoExpediente; }
        public void setEstadoExpediente(EstadoExpediente estadoExpediente) { this.estadoExpediente = estadoExpediente; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
        public FechaPojo getFecha() { return fecha; }
        public void setFecha(FechaPojo fecha) { this.fecha = fecha; }
        public List<PlanoPojo> getPlanos() { return planos; }
        public void setPlanos(List<PlanoPojo> planos) { this.planos = planos; }
    }

    public static class FechaPojo {
        private Integer dia;
        private Integer mes;
        private Integer anio;
        // Getters y Setters
        public Integer getDia() { return dia; }
        public void setDia(Integer dia) { this.dia = dia; }
        public Integer getMes() { return mes; }
        public void setMes(Integer mes) { this.mes = mes; }
        public Integer getAnio() { return anio; }
        public void setAnio(Integer anio) { this.anio = anio; }
    }

    public static class PlanoPojo {
        private Long idPlano;
        private TipoPlano tipoPlano;
        private String nombreArchivo;
        // Getters y Setters
        public Long getIdPlano() { return idPlano; }
        public void setIdPlano(Long idPlano) { this.idPlano = idPlano; }
        public TipoPlano getTipoPlano() { return tipoPlano; }
        public void setTipoPlano(TipoPlano tipoPlano) { this.tipoPlano = tipoPlano; }
        public String getNombreArchivo() { return nombreArchivo; }
        public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    }

    // --- Getters y Setters de la clase principal ---
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
    public List<DocumentoLegalPojo> getDocumentosLegales() { return documentosLegales; }
    public void setDocumentosLegales(List<DocumentoLegalPojo> documentosLegales) { this.documentosLegales = documentosLegales; }
    public List<ServicioPojo> getServicios() { return servicios; }
    public void setServicios(List<ServicioPojo> servicios) { this.servicios = servicios; }
    public ExpedientePojo getExpediente() { return expediente; }
    public void setExpediente(ExpedientePojo expediente) { this.expediente = expediente; }

    //ENUMS
    public enum EstadoDocumentoLegal { VALIDO, OBSERVADO, VENCIDO, NO_PRESENTADO }
    public enum EstadoExpediente { PENDIENTE, EN_PROCESO, APROBADO, OBSERVADO, RECHAZADO }
    public enum EstadoPlano { PENDIENTE, APROBADO, RECHAZADO, OBSERVADO }
    public enum EstadoPropiedad { DISPONIBLE, OCUPADO, EN_MANTENIMIENTO, VENDIDO }
    public enum EstadoServicio { ACTIVO, INACTIVO, SUSPENDIDO, PENDIENTE }
    public enum TipoDocumentoLegal { ESCRITURA_PUBLICA, CERTIFICADO_POSESION, CONTRATO_COMPRAVENTA }
    public enum TipoExpediente { HABILITACION_URBANA, INSCRIPCION_SUNARP, CONTRATO_COMPRAVENTA }
    public enum TipoPlano { ARQUITECTONICO, UBICACION, TOPOGRAFICO, ESTRUCTURAL }
    public enum TipoPropiedad { INTERNA, TERCERO }
    public enum TipoServicio { ELECTRICIDAD, AGUA, GAS, INTERNET, DESAGUE, OTRO }
}