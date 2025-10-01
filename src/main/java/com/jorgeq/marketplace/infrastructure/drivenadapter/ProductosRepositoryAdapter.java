package com.jorgeq.marketplace.infrastructure.drivenadapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jorgeq.marketplace.domain.gateways.ProductosRepository;
import com.jorgeq.marketplace.domain.model.ProductoDto;

@Repository
public class ProductosRepositoryAdapter implements ProductosRepository {

    private final List<ProductoDto> productos;

    public ProductosRepositoryAdapter(List<ProductoDto> productos) {
        this.productos = productos;
    }

    @Override
    public List<ProductoDto> findAll() {
        return productos;
    }

    @Override
    public ProductoDto findById(String id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
