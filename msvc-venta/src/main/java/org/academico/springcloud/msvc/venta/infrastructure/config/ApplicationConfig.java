package org.academico.springcloud.msvc.venta.infrastructure.config;

import org.academico.springcloud.msvc.venta.application.services.VentaService;
import org.academico.springcloud.msvc.venta.application.usecases.*;
import org.academico.springcloud.msvc.venta.domain.ports.in.*;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
// NUEVOS IMPORTS
import org.academico.springcloud.msvc.venta.domain.ports.out.PreventaPort;
import org.academico.springcloud.msvc.venta.infrastructure.adapters.ExternalPreventaAdapter;
import org.academico.springcloud.msvc.venta.infrastructure.clients.PreventaClientRest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public VentaService ventaService(
            CreateVentaUseCase createVentaUseCase,
            DeleteVentaUseCase deleteVentaUseCase,
            GetVentaUseCase getVentaUseCase,
            UpdateVentaUseCase updateVentaUseCase,
            ManageDetalleVentaUseCase manageDetalleVentaUseCase,
            CountVentasUseCase countVentasUseCase,
            AsignarPreventaUseCase asignarPreventaUseCase) { // MODIFICADO: Nuevo parámetro
        return new VentaService(
                createVentaUseCase,
                deleteVentaUseCase,
                getVentaUseCase,
                updateVentaUseCase,
                manageDetalleVentaUseCase,
                countVentasUseCase,
                asignarPreventaUseCase // NUEVO: Inyecta el nuevo caso de uso
        );
    }

    // --- Definición de los Use Cases (Application Layer) ---
    // (Estos son los que ya tenías, solo los incluyo para contexto, no cambian)
    @Bean
    public CreateVentaUseCase createVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new CreateVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public DeleteVentaUseCase deleteVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new DeleteVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public GetVentaUseCase getVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new GetVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public UpdateVentaUseCase updateVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new UpdateVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public ManageDetalleVentaUseCase manageDetalleVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new ManageDetalleVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public CountVentasUseCase countVentasUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new CountVentasUseCaseImpl(ventaRepositoryPort);
    }

    // NUEVO Bean para AsignarPreventaUseCase
    @Bean
    public AsignarPreventaUseCase asignarPreventaUseCase(VentaRepositoryPort ventaRepositoryPort, PreventaPort preventaPort) {
        return new AsignarPreventaUseCaseImpl(ventaRepositoryPort, preventaPort);
    }

    // --- Definición del Adaptador de persistencia (Infrastructure Layer) ---
    // (Este es el que ya tenías, no cambia)
    @Bean
    public VentaRepositoryPort ventaRepositoryPort(
            org.academico.springcloud.msvc.venta.infrastructure.repositories.JpaVentaRepository jpaVentaRepository) {
        return new org.academico.springcloud.msvc.venta.infrastructure.adapters.JpaVentaRepositoryAdapter(jpaVentaRepository);
    }

    // NUEVO Bean para el Puerto de Salida de Preventa
    @Bean
    public PreventaPort preventaPort(PreventaClientRest preventaClientRest) {
        return new ExternalPreventaAdapter(preventaClientRest);
    }

    // NO SE NECESITA UN @Bean EXPLÍCITO para PreventaClientRest,
    // ya que @FeignClient se encarga de que Spring lo cree.
}