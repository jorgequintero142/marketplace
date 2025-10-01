package com.jorgeq.marketplace.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorgeq.marketplace.domain.model.ProductoDto;

@Configuration
public class ProductosConfig {

    @Bean
    public List<ProductoDto> productosData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("productos.json");
            InputStream inputStream = resource.getInputStream();
            
            return objectMapper.readValue(inputStream, new TypeReference<List<ProductoDto>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error cargando productos.json", e);
        }
    }
}
