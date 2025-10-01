package com.jorgeq.marketplace.infrastructure.drivenadapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.gateways.ProductosRepository;
import com.jorgeq.marketplace.domain.model.ComparacionDto;

@Repository
public class ComparadorProductosRepositoryAdapter implements ComparadorProductosRepository {
    private final ProductosRepository productosRepository;


    public ComparadorProductosRepositoryAdapter(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }
    @Override
    public ComparacionDto compararProductos(List<String> codigosProductos) {
        ComparacionDto comparacionDto = ComparacionDto.builder()
        .productos(productosRepository.findAll())
        .build();
        return comparacionDto;
    }

    @Override
    public ComparacionDto compararProductosPorCaracteristica(List<String> codigosProductos, String caracteristica) {
        return null;
    }

}
