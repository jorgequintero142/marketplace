package com.jorgeq.marketplace.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuración centralizada de OpenAPI/Swagger
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Marketplace API")
                        .description("API para comparación de productos del marketplace")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jorge Q")
                                .email("jorge@marketplace.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor de desarrollo"))
                .addServersItem(new Server()
                        .url("https://api.marketplace.com")
                        .description("Servidor de producción"));
    }
}
