package com.jorgeq.marketplace.domain.gateways;

import java.util.List;

import com.jorgeq.marketplace.domain.model.ProductoDto;

public interface ProductosRepository {
    List<ProductoDto> findAll();
    ProductoDto findById(String id);
}
