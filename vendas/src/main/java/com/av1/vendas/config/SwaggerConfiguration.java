package com.av1.vendas.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("vendas")
                .pathsToMatch("/**")
                .packagesToScan("com.av1.vendas.resources")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI(){
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("AV1 Jefferson")
                        .description("AV1 Jefferson APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("AV1")
                                .url("http://www.av1jefferson.com.br")
                                .email("av1jefferson@email.com")));
    }
}
