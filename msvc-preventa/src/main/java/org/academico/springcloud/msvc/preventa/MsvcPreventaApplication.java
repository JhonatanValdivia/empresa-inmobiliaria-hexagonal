package org.academico.springcloud.msvc.preventa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("org.academico.springcloud.msvc.preventa.infrastructure.entities")
@EnableFeignClients(basePackages = "org.academico.springcloud.msvc.preventa.infrastructure.clients")
public class MsvcPreventaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsvcPreventaApplication.class, args);
	}
}
