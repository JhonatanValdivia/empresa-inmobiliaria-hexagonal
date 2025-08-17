package org.academico.springcloud.msvc.campania.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Propiedad {

    @JsonProperty("id")  // <-- Esto asegura que "id" del JSON se mapea a idPropiedad
    private Long idPropiedad;
    private String tipoPropiedad;
    private String estado;
    private Precio precio;
    private Ubicacion ubicacion;
    private Zonificacion zonificacion;

    public Propiedad() {}

    public Propiedad(Long idPropiedad, String tipoPropiedad, String estado, Precio precio, Ubicacion ubicacion, Zonificacion zonificacion) {
        this.idPropiedad = idPropiedad;
        this.tipoPropiedad = tipoPropiedad;
        this.estado = estado;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.zonificacion = zonificacion;
    }

    public Long getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(Long idPropiedad) { this.idPropiedad = idPropiedad; }
    public String getTipoPropiedad() { return tipoPropiedad; }
    public void setTipoPropiedad(String tipoPropiedad) { this.tipoPropiedad = tipoPropiedad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Precio getPrecio() { return precio; }
    public void setPrecio(Precio precio) { this.precio = precio; }
    public Ubicacion getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
    public Zonificacion getZonificacion() { return zonificacion; }
    public void setZonificacion(Zonificacion zonificacion) { this.zonificacion = zonificacion; }

    // Value objects as static inner classes
    public static class Precio {
        private BigDecimal monto;
        private String moneda;

        public Precio() {}

        public Precio(BigDecimal monto, String moneda) {
            this.monto = monto;
            this.moneda = moneda;
        }

        public BigDecimal getMonto() { return monto; }
        public void setMonto(BigDecimal monto) { this.monto = monto; }
        public String getMoneda() { return moneda; }
        public void setMoneda(String moneda) { this.moneda = moneda; }
    }

    public static class Ubicacion {
        private String ubigeo;
        private String ciudad;
        private String direccion;

        public Ubicacion() {}

        public Ubicacion(String ubigeo, String ciudad, String direccion) {
            this.ubigeo = ubigeo;
            this.ciudad = ciudad;
            this.direccion = direccion;
        }

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

        public Zonificacion() {}

        public Zonificacion(String tipoZona, String descripcionNormativa, String usoPermitido) {
            this.tipoZona = tipoZona;
            this.descripcionNormativa = descripcionNormativa;
            this.usoPermitido = usoPermitido;
        }

        public String getTipoZona() { return tipoZona; }
        public void setTipoZona(String tipoZona) { this.tipoZona = tipoZona; }
        public String getDescripcionNormativa() { return descripcionNormativa; }
        public void setDescripcionNormativa(String descripcionNormativa) { this.descripcionNormativa = descripcionNormativa; }
        public String getUsoPermitido() { return usoPermitido; }
        public void setUsoPermitido(String usoPermitido) { this.usoPermitido = usoPermitido; }
    }
}