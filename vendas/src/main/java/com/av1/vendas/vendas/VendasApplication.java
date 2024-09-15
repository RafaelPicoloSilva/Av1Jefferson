package com.av1.vendas.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.av1.vendas.domains", "com.av1.vendas.domains.enums"})
@ComponentScan(basePackages = "com.av1.vendas")
@EnableJpaRepositories(basePackages = "com.av1.vendas.repositories")
@SpringBootApplication
public class VendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
