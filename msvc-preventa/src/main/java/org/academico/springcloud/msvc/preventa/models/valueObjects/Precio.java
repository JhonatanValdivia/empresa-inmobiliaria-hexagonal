package org.academico.springcloud.msvc.preventa.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Precio {
    private BigDecimal monto;
    private String moneda;

    protected Precio(){}
    @JsonCreator
    public Precio(@JsonProperty("monto") BigDecimal montoComision,
                         @JsonProperty("moneda") String moneda) {
        if (moneda == null || moneda.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser vacía");
        }
        this.monto = montoComision;
        this.moneda = moneda;
    }

    public BigDecimal getMontoComision () {
        return monto;
    }
    public String getMoneda() {
        return moneda;
    }

    @Override
    public boolean equals(Object object){
        if(this==object)
            return true;

        if(!(object instanceof Precio))
            return false;

        Precio precio=(Precio) object;
        return monto.equals(precio.monto) &&
                moneda.equals(precio.moneda);
    }
    @Override
    public int hashCode(){
        return Objects.hash(monto,moneda);
    }

    @Override
    public String toString(){
        return monto+ " " +moneda;
    }
}
