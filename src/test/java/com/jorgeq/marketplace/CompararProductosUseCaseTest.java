package com.jorgeq.marketplace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jorgeq.marketplace.domain.exceptions.ParametroConErrorException;
import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.model.ComparacionDto;
import com.jorgeq.marketplace.domain.model.Constantes;
import com.jorgeq.marketplace.domain.model.ProductoDto;
import com.jorgeq.marketplace.usecases.CompararProductosUseCase;

public class CompararProductosUseCaseTest {
    private ComparadorProductosRepository repository;
    private CompararProductosUseCase compararProductosUseCase;
    private final String CRITERIO_COMPARAR = "precio";

    private final ProductoDto productoMasCostoso = new ProductoDto(
            "PR04", "Producto 4", "img4.png", "Producto descripcion 4", BigDecimal.valueOf(28000), 4.0d,
            "Especificaciones producto 4");

    private final ProductoDto productoMasEconomico = new ProductoDto(
            "PR05", "Producto 5", "img5.png", "Producto descripcion 5", BigDecimal.valueOf(2800), 2.7d,
            "Especificaciones producto 5");

    private final ProductoDto productoMasPuntuado = new ProductoDto(
            "PR01", "Producto 1", "img1.png", "Producto descripcion 1", BigDecimal.valueOf(8000), 4.5d,
            "Especificaciones producto 1");

    private List<ProductoDto> productosDisponibles = List.of(productoMasCostoso, productoMasEconomico,
            productoMasPuntuado);

    @BeforeEach
    void setUp() {

        repository = mock(ComparadorProductosRepository.class);
        compararProductosUseCase = new CompararProductosUseCase(repository);
    }

    @Test
    void compararProductosOk() {

        List<String> prod = List.of("PR1", "PR2", "PR3");
        when(repository.buscarProductosComparar(anyList())).thenReturn(productosDisponibles);
        ComparacionDto resultadoComparar = compararProductosUseCase.compararProductos(prod);

        assertNotNull(resultadoComparar);
        assertEquals(3, resultadoComparar.getProductos().size());
        assertNotNull(resultadoComparar.getCriteriosRecomendados());
        assertEquals(productoMasCostoso.getNombre(),
                resultadoComparar.getCriteriosRecomendados().getMasCostoso().getNombre());
        assertEquals(productoMasPuntuado.getNombre(),
                resultadoComparar.getCriteriosRecomendados().getMasRating().getNombre());

    }

    @Test
    void compararProductosListaVacia() {
        ParametroConErrorException exception = assertThrows(
                ParametroConErrorException.class,
                () -> compararProductosUseCase.compararProductos(null));
        assertEquals(Constantes.ERROR_PRODUCTOS_VACIOS, exception.getMessage());
    }

    @Test
    void compararProductosRepetidosMenorDos() {
        ParametroConErrorException exception = assertThrows(
                ParametroConErrorException.class,
                () -> compararProductosUseCase.compararProductos(List.of("01", "01")));
        assertEquals(Constantes.ERROR_PRODUCTOS_CANTIDAD, exception.getMessage());
    }

    @Test
    void compararProductosPorCriterioOkAsc() {
        List<String> productos = List.of("PR1", "PR2", "PR3");
        when(repository.buscarProductosComparar(anyList())).thenReturn(productosDisponibles);
        List<ProductoDto> resultadoComparar = compararProductosUseCase.compararProductosPorCriterio(CRITERIO_COMPARAR,
                productos, true);
        assertNotNull(resultadoComparar);
        assertEquals(3, resultadoComparar.size());
        assertEquals(productoMasEconomico.getPrecio(), resultadoComparar.get(0).getPrecio());
    }

    @Test
    void compararProductosPorCriterioOkDesc() {
        List<String> productos = List.of("PR1", "PR2", "PR3");
        when(repository.buscarProductosComparar(anyList())).thenReturn(productosDisponibles);
        List<ProductoDto> resultadoComparar = compararProductosUseCase.compararProductosPorCriterio(CRITERIO_COMPARAR,
                productos, false);
        assertNotNull(resultadoComparar);
        assertEquals(3, resultadoComparar.size());
        assertEquals(productoMasCostoso.getPrecio(), resultadoComparar.get(0).getPrecio());
    }

    @Test
    void compararProductosPorCriterioFail() {
        List<String> productos = List.of("PR1", "PR2", "PR3");
        ParametroConErrorException exception = assertThrows(
                ParametroConErrorException.class,
                () -> compararProductosUseCase.compararProductosPorCriterio("NO_EXISTO", productos, true));
        assertEquals(Constantes.ERROR_CRITERO_BUSQUEDA, exception.getMessage());
    }
}
