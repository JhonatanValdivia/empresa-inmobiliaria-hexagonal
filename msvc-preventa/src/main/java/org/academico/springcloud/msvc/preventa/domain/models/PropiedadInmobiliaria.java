package org.academico.springcloud.msvc.preventa.domain.models;

import java.math.BigDecimal;

public class PropiedadInmobiliaria {

    private Long id;

    // Características principales
    private TipoPropiedad tipoPropiedad;
    private EstadoPropiedad estado;

    // Atributos aplanados de Precio
    private BigDecimal precioMonto;
    private String precioMoneda;

    // Atributos aplanados de Ubicacion
    private String ubicacionUbigeo;
    private String ubicacionCiudad;
    private String ubicacionDireccion;

    // Atributos aplanados de Zonificacion
    private String zonificacionTipoZona;
    private String zonificacionDescripcionNormativa;
    private String zonificacionUsoPermitido;

    // Constructores
    public PropiedadInmobiliaria() {}

    public PropiedadInmobiliaria(Long id, TipoPropiedad tipoPropiedad, EstadoPropiedad estado,
                                 BigDecimal precioMonto, String precioMoneda,
                                 String ubicacionUbigeo, String ubicacionCiudad, String ubicacionDireccion,
                                 String zonificacionTipoZona, String zonificacionDescripcionNormativa, String zonificacionUsoPermitido) {
        this.id = id;
        this.tipoPropiedad = tipoPropiedad;
        this.estado = estado;
        this.precioMonto = precioMonto;
        this.precioMoneda = precioMoneda;
        this.ubicacionUbigeo = ubicacionUbigeo;
        this.ubicacionCiudad = ubicacionCiudad;
        this.ubicacionDireccion = ubicacionDireccion;
        this.zonificacionTipoZona = zonificacionTipoZona;
        this.zonificacionDescripcionNormativa = zonificacionDescripcionNormativa;
        this.zonificacionUsoPermitido = zonificacionUsoPermitido;
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TipoPropiedad getTipoPropiedad() { return tipoPropiedad; }
    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) { this.tipoPropiedad = tipoPropiedad; }
    public EstadoPropiedad getEstado() { return estado; }
    public void setEstado(EstadoPropiedad estado) { this.estado = estado; }
    public BigDecimal getPrecioMonto() { return precioMonto; }
    public void setPrecioMonto(BigDecimal precioMonto) { this.precioMonto = precioMonto; }
    public String getPrecioMoneda() { return precioMoneda; }
    public void setPrecioMoneda(String precioMoneda) { this.precioMoneda = precioMoneda; }
    public String getUbicacionUbigeo() { return ubicacionUbigeo; }
    public void setUbicacionUbigeo(String ubicacionUbigeo) { this.ubicacionUbigeo = ubicacionUbigeo; }
    public String getUbicacionCiudad() { return ubicacionCiudad; }
    public void setUbicacionCiudad(String ubicacionCiudad) { this.ubicacionCiudad = ubicacionCiudad; }
    public String getUbicacionDireccion() { return ubicacionDireccion; }
    public void setUbicacionDireccion(String ubicacionDireccion) { this.ubicacionDireccion = ubicacionDireccion; }
    public String getZonificacionTipoZona() { return zonificacionTipoZona; }
    public void setZonificacionTipoZona(String zonificacionTipoZona) { this.zonificacionTipoZona = zonificacionTipoZona; }
    public String getZonificacionDescripcionNormativa() { return zonificacionDescripcionNormativa; }
    public void setZonificacionDescripcionNormativa(String zonificacionDescripcionNormativa) { this.zonificacionDescripcionNormativa = zonificacionDescripcionNormativa; }
    public String getZonificacionUsoPermitido() { return zonificacionUsoPermitido; }
    public void setZonificacionUsoPermitido(String zonificacionUsoPermitido) { this.zonificacionUsoPermitido = zonificacionUsoPermitido; }



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
    public enum TipoUsuario { CLIENTE, AGENTE, PROPIETARIO }
}