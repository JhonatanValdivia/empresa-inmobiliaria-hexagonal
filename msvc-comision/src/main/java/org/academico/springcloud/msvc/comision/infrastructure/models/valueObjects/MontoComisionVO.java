package org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class MontoComisionVO {
    private BigDecimal montoComision;
    private String moneda;
    protected MontoComisionVO(){}

    @JsonCreator
    public MontoComisionVO(@JsonProperty("precioVenta") BigDecimal montoComision,
                           @JsonProperty("moneda") String moneda) {
        this.montoComision = montoComision;
        this.moneda = moneda;
    }

    public BigDecimal getMontoComision () {
        return montoComision;
    }
    public String getMoneda() {
        return moneda;
    }
}
