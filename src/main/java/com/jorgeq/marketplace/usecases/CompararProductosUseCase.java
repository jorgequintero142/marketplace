package com.jorgeq.marketplace.usecases;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.model.ComparacionDto;
import com.jorgeq.marketplace.domain.model.Constantes;
import com.jorgeq.marketplace.domain.model.CriterioPrecioDto;
import com.jorgeq.marketplace.domain.model.CriterioRankingDto;
import com.jorgeq.marketplace.domain.model.CriteriosRecomendadosDto;
import com.jorgeq.marketplace.domain.model.ProductoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CompararProductosUseCase {
    private final ComparadorProductosRepository comparadorProductosRepository;

    public ComparacionDto compararProductos(List<String> codigosProductos) {
        log.debug("Iniciando comparacion de productos con códigos {}", codigosProductos);

        validarCodigos(codigosProductos);

        List<ProductoDto> productosComparar = comparadorProductosRepository.buscarProductosComparar(codigosProductos);
        CriteriosRecomendadosDto criteriosRecomendadosDto = generarCriterios(productosComparar);
        
        ComparacionDto comparacionDto = ComparacionDto.builder()
                .criteriosRecomendados(criteriosRecomendadosDto)
                .productos(productosComparar)
                .build();

        log.debug("Finalizacion de comparacion");
        return comparacionDto;

    }

    private CriteriosRecomendadosDto generarCriterios(List<ProductoDto> productosComparar) {
        List<ProductoDto> recomendacionPrecio = encontrarExtremos(productosComparar, ProductoDto::getPrecio);
        List<ProductoDto> recomendacionRating = encontrarExtremos(productosComparar, ProductoDto::getRating);

        ProductoDto productoMasCostoso = recomendacionPrecio.get(1);
        ProductoDto productomasEconomico = recomendacionPrecio.get(0);

        ProductoDto productoMasRating = recomendacionRating.get(1);
        ProductoDto productoMenosRating = recomendacionRating.get(0);

        CriterioRankingDto criterioMasRating = createCriterioRating(productoMasRating);
        CriterioRankingDto criterioMenosRating = createCriterioRating(productoMenosRating);

        CriterioPrecioDto criterioMasCostoso = createCriterioPrecio(productoMasCostoso);
        CriterioPrecioDto criterioMasEconomico = createCriterioPrecio(productomasEconomico);
        return generarCriteriosRecomendados(criterioMasCostoso,
                criterioMasEconomico, criterioMasRating, criterioMenosRating);
    }

    private CriterioPrecioDto createCriterioPrecio(ProductoDto productoDto) {
        return CriterioPrecioDto.builder()
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .build();
    }

    private CriterioRankingDto createCriterioRating(ProductoDto productoDto) {
        return CriterioRankingDto.builder()
                .nombre(productoDto.getNombre())
                .ranking(productoDto.getRating())
                .build();
    }

    private CriteriosRecomendadosDto generarCriteriosRecomendados(CriterioPrecioDto criterioMasCostoso,
            CriterioPrecioDto criterioMasEconomico, CriterioRankingDto criterioMasRating,
            CriterioRankingDto criterioMenosRating) {
        return CriteriosRecomendadosDto
                .builder()
                .masRating(criterioMasRating)
                .menosRating(criterioMenosRating)
                .masCostoso(criterioMasCostoso)
                .masEconomico(criterioMasEconomico)
                .build();
    }

    private void validarCodigos(List<String> codigosProductos) {

        if (codigosProductos == null || codigosProductos.isEmpty()) {
            log.error("Validacion fallida, lista de productos no puede estar vacía");
            throw new IllegalArgumentException(Constantes.ERROR_PRODUCTOS_VACIOS);
        }

        if (codigosProductos.size() < Constantes.MIN_PRODUCTOS_COMPARAR
                || codigosProductos.size() > Constantes.MAX_PRODUCTOS_COMPARAR) {
            log.error("Validacion fallida , cantidad minima");
            throw new IllegalArgumentException(Constantes.ERROR_PRODUCTOS_CANTIDAD);
        }
    }

    private <T extends Comparable<? super T>> List<ProductoDto> encontrarExtremos(
            List<ProductoDto> productos,
            Function<ProductoDto, T> extractor) {

        return productos.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                extractor,
                                p -> p,
                                (p1, p2) -> p1,
                                () -> new TreeMap<>(Comparator.naturalOrder())),
                        map -> List.of(map.firstEntry().getValue(), map.lastEntry().getValue())));
    }

}
