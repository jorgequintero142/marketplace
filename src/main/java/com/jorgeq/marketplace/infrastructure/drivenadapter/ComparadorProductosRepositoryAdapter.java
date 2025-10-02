package com.jorgeq.marketplace.infrastructure.drivenadapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.gateways.ProductosRepository;
import com.jorgeq.marketplace.domain.model.ProductoDto;

@Repository
public class ComparadorProductosRepositoryAdapter implements ComparadorProductosRepository {
    private final ProductosRepository productosRepository;

    public ComparadorProductosRepositoryAdapter(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<ProductoDto> buscarProductosComparar(List<String> codigosProductos) {
        return productosRepository.findAll().stream().filter(t -> codigosProductos.contains(t.getId())).toList();
    }
}
