package com.jorgeq.marketplace.domain.gateways;

import java.util.List;

import com.jorgeq.marketplace.domain.model.ProductoDto;

public interface ComparadorProductosRepository {
    List<ProductoDto> compararProductos(List<String> codigosProductos);
    List<ProductoDto> compararProductosPorCaracteristica(List<String> codigosProductos, String caracteristica);
}
