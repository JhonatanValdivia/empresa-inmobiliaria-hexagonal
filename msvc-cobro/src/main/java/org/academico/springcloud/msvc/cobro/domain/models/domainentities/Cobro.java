package org.academico.springcloud.msvc.cobro.domain.models.domainentities;

import org.academico.springcloud.msvc.cobro.domain.models.enums.EstadoCobro;
import org.academico.springcloud.msvc.cobro.domain.models.enums.MedioPago;
import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.FechaCobro;
import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.MontoCobro;

import java.math.BigDecimal;

public class Cobro {

    private Long idCobro;
    private Long idVenta; // El ID de la venta es obligatorio
    private FechaCobro fechaCobro;
    private MontoCobro montoCobro;
    private EstadoCobro estadoCobro;
    private MedioPago medioPago;

    // Constructor vacío (puede ser útil para ciertos mapeos o frameworks, aunque se prefiere el constructor con todos los campos)
    public Cobro() {
        this.estadoCobro = EstadoCobro.PENDIENTE; // Valor por defecto
    }

    public Cobro(Long idCobro, Long idVenta, FechaCobro fechaCobro, MontoCobro montoCobro, EstadoCobro estadoCobro, MedioPago medioPago) {
        if (idVenta == null) {
            throw new IllegalArgumentException("El ID de la venta es obligatorio.");
        }
        if (fechaCobro == null) {
            throw new IllegalArgumentException("La fecha de cobro es obligatoria.");
        }
        if (montoCobro == null) {
            throw new IllegalArgumentException("El monto de cobro es obligatorio.");
        }

        this.idCobro = idCobro;
        this.idVenta = idVenta;
        this.fechaCobro = fechaCobro;
        this.montoCobro = montoCobro;
        this.estadoCobro = (estadoCobro != null) ? estadoCobro : EstadoCobro.PENDIENTE; // Permite inicializar o usar el valor por defecto
        this.medioPago = medioPago;
    }

    // Métodos de la lógica de negocio (del agregado)
    public void registrarPago(BigDecimal montoPagado) {
        if (montoPagado == null || montoPagado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor que cero.");
        }
        if (!EstadoCobro.PENDIENTE.equals(this.estadoCobro)) {
            throw new IllegalStateException("El cobro ya ha sido procesado.");
        }
        // Aquí puedes añadir más lógica de negocio, por ejemplo, si el pago es parcial
        // Para este ejemplo, asumimos que un pago lo marca como PAGADO y actualiza el monto.
        this.montoCobro = new MontoCobro(montoPagado, this.montoCobro.getMoneda()); // Actualiza el monto con el pago
        this.estadoCobro = EstadoCobro.PAGADO;
        // Podrías registrar el MedioPago aquí si se recibe en registrarPago
    }

    public String generarComprobante() {
        // En una aplicación real, esto probablemente interactuaría con un servicio externo o una plantilla.
        // Aquí lo mantenemos simple como un método de dominio.
        return String.format("Comprobante #%d\nFecha: %s\nMonto: %s %s\nEstado: %s\nMedio: %s",
                idCobro,
                fechaCobro.toString(),
                montoCobro.getMonto(), montoCobro.getMoneda(),
                estadoCobro,
                medioPago != null ? medioPago.name() : "N/A");
    }

    // Getters y Setters
    public Long getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Long idCobro) {
        this.idCobro = idCobro;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public FechaCobro getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(FechaCobro fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public MontoCobro getMontoCobro() {
        return montoCobro;
    }

    public void setMontoCobro(MontoCobro montoCobro) {
        this.montoCobro = montoCobro;
    }

    public EstadoCobro getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(EstadoCobro estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}