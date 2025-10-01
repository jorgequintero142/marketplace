package com.jorgeq.marketplace.domain.gateways;

import java.util.List;

import com.jorgeq.marketplace.domain.model.ComparacionDto;

public interface ComparadorProductosRepository {
    ComparacionDto compararProductos(List<String> codigosProductos);
    ComparacionDto compararProductosPorCaracteristica(List<String> codigosProductos, String caracteristica);
}
