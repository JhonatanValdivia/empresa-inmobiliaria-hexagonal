package org.academico.springcloud.msvc.comision.domain.models.valueObjects;
import java.math.BigDecimal;
import java.util.Objects;

public class MontoComision {
    private BigDecimal montoComision;
    private String moneda;

    protected  MontoComision(){}

    public MontoComision(BigDecimal montoComision,String moneda) {
        if (moneda == null || moneda.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser vacía");
        }
        // No validar montoComision aquí para evitar errores de deserialización
        this.montoComision = montoComision;
        this.moneda = moneda;
    }

    public BigDecimal getMontoComision () {
        return montoComision;
    }
    public String getMoneda() {
        return moneda;
    }

    @Override
    public boolean equals(Object object){
        if(this==object)
            return true;

        if(!(object instanceof MontoComision))
            return false;

        MontoComision monto=(MontoComision) object;
        return montoComision.equals(monto.montoComision) &&
                moneda.equals(monto.moneda);
    }
    @Override
    public int hashCode(){
        return Objects.hash(montoComision,moneda);
    }

    @Override
    public String toString(){
        return montoComision + " " +moneda;
    }
}
