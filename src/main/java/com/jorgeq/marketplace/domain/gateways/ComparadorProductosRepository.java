package com.jorgeq.marketplace.domain.gateways;

import java.util.List;

import com.jorgeq.marketplace.domain.model.ProductoDto;

public interface ComparadorProductosRepository {
    List<ProductoDto> buscarProductosComparar(List<String> codigosProductos);
}
