package org.academico.springcloud.msvc.campania.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class PropiedadOV {

    private Long idPropiedad;
    private String tipoPropiedad;
    private String estado;
    private PrecioOV precio;
    private UbicacionOV ubicacion;
    private ZonificacionOV zonificacion;

    protected PropiedadOV() {}

    @JsonCreator
    public PropiedadOV(@JsonProperty("id") Long idPropiedad,
                       @JsonProperty("tipoPropiedad") String tipoPropiedad,
                       @JsonProperty("estado") String estado,
                       @JsonProperty("precio") PrecioOV precio,
                       @JsonProperty("ubicacion") UbicacionOV ubicacion,
                       @JsonProperty("zonificacion") ZonificacionOV zonificacion) {
        this.idPropiedad = idPropiedad;
        this.tipoPropiedad = tipoPropiedad;
        this.estado = estado;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.zonificacion = zonificacion;
    }

    public Long getIdPropiedad() { return idPropiedad; }
    public String getTipoPropiedad() { return tipoPropiedad; }
    public String getEstado() { return estado; }
    public PrecioOV getPrecio() { return precio; }
    public UbicacionOV getUbicacion() { return ubicacion; }
    public ZonificacionOV getZonificacion() { return zonificacion; }

    // Subclases anidadas (simples, sin validaciones)
    @Embeddable
    public static class PrecioOV {
        private BigDecimal monto;
        private String moneda;

        protected PrecioOV() {}

        @JsonCreator
        public PrecioOV(@JsonProperty("monto") BigDecimal monto,
                        @JsonProperty("moneda") String moneda) {
            this.monto = monto;
            this.moneda = moneda;
        }

        public BigDecimal getMonto() { return monto; }
        public String getMoneda() { return moneda; }
    }

    @Embeddable
    public static class UbicacionOV {
        private String ubigeo;
        private String ciudad;
        private String direccion;

        protected UbicacionOV() {}

        @JsonCreator
        public UbicacionOV(@JsonProperty("ubigeo") String ubigeo,
                           @JsonProperty("ciudad") String ciudad,
                           @JsonProperty("direccion") String direccion) {
            this.ubigeo = ubigeo;
            this.ciudad = ciudad;
            this.direccion = direccion;
        }

        public String getUbigeo() { return ubigeo; }
        public String getCiudad() { return ciudad; }
        public String getDireccion() { return direccion; }
    }

    @Embeddable
    public static class ZonificacionOV {
        private String tipoZona;
        private String descripcionNormativa;
        private String usoPermitido;

        protected ZonificacionOV() {}

        @JsonCreator
        public ZonificacionOV(@JsonProperty("tipoZona") String tipoZona,
                              @JsonProperty("descripcionNormativa") String descripcionNormativa,
                              @JsonProperty("usoPermitido") String usoPermitido) {
            this.tipoZona = tipoZona;
            this.descripcionNormativa = descripcionNormativa;
            this.usoPermitido = usoPermitido;
        }

        public String getTipoZona() { return tipoZona; }
        public String getDescripcionNormativa() { return descripcionNormativa; }
        public String getUsoPermitido() { return usoPermitido; }
    }
}
