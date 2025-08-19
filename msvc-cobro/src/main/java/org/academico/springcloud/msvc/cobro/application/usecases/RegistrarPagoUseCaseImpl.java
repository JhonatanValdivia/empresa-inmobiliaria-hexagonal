package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.MontoCobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.RegistrarPagoUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public class RegistrarPagoUseCaseImpl implements RegistrarPagoUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public RegistrarPagoUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional
    public Optional<Cobro> registrarPago(Long idCobro, BigDecimal montoPago) {
        return cobroRepositoryPort.findById(idCobro).map(cobro -> {
            // Validación de monto pagado vs monto original, si se requiere a nivel de caso de uso
            // Esta lógica es específica de este caso de uso y no es intrínseca a 'Cobro' en sí.
            if (montoPago.compareTo(cobro.getMontoCobro().getMonto()) != 0) {
                // Aquí puedes decidir si quieres lanzar una excepción, ajustar el monto, etc.
                // Por simplicidad, se registra con el monto exacto del pago y la moneda original.
                // Si se quiere mantener el monto original del cobro y solo registrar el pago recibido,
                // la lógica de `Cobro.registrarPago` debería ser diferente (ej. añadir a una lista de pagos).
                // Para este ejemplo, actualizamos el monto del cobro como se hizo en tu código original.
                // También se podría validar aquí si el montoPago es mayor que el monto original,
                // como se hacía en el controlador anterior, pero se prefiere que esa validación
                // sea manejada por el dominio o por un validador explícito en el caso de uso.
            }
            try {
                cobro.registrarPago(montoPago); // Llama al método de dominio
            } catch (IllegalArgumentException | IllegalStateException e) {
                // Relanza excepciones de dominio como RuntimeException o excepciones de aplicación específicas
                throw new RuntimeException("Error al registrar el pago: " + e.getMessage(), e);
            }
            return cobroRepositoryPort.save(cobro); // Guarda el estado actualizado del dominio
        });
    }
}