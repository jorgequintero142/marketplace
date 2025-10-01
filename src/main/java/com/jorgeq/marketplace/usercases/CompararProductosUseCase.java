package com.jorgeq.marketplace.usercases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.model.ComparacionDto;
import com.jorgeq.marketplace.domain.model.CriterioPrecioDto;
import com.jorgeq.marketplace.domain.model.CriterioRankingDto;
import com.jorgeq.marketplace.domain.model.CriteriosRecomendadosDto;
import com.jorgeq.marketplace.domain.model.ProductoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompararProductosUseCase {
    private final ComparadorProductosRepository comparadorProductosRepository;

    public ComparacionDto compararProductos(List<String> codigosProductos) {

        List<ProductoDto> productosComparar = comparadorProductosRepository.buscarProductosComparar(codigosProductos);

        List<ProductoDto> recomendacionPrecio = generarRecomendacionPrecio(productosComparar);
        ProductoDto productoMasCostoso = recomendacionPrecio.get(1);
        ProductoDto productomasEconomico = recomendacionPrecio.get(0);

        CriterioPrecioDto criterioMasCostoso = createCriterioPrecio(productoMasCostoso);
        CriterioPrecioDto criterioMasEconomico = createCriterioPrecio(productomasEconomico);



        List<ProductoDto> recomendacionRating = generarRecomendacionRating(productosComparar);
        ProductoDto productoMasRating = recomendacionRating.get(1);
        ProductoDto productoMenosRating = recomendacionRating.get(0);

        CriterioRankingDto criterioMasRating = createCriterioRating(productoMasRating);
        CriterioRankingDto criterioMenosRating = createCriterioRating(productoMenosRating);

        CriteriosRecomendadosDto criteriosRecomendadosDto = generarCriteriosRecomendados(criterioMasCostoso,
                criterioMasEconomico, criterioMasRating, criterioMenosRating);
        ComparacionDto comparacionDto = ComparacionDto.builder()
                .criteriosRecomendados(criteriosRecomendadosDto)
                .productos(productosComparar)
                .build();

        return comparacionDto;

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

    private List<ProductoDto> generarRecomendacionPrecio(List<ProductoDto> productos) {
        productos = productos.stream().sorted((o1, o2) -> o1.getPrecio().compareTo(o2.getPrecio())).toList();
        int size = productos.size();
        ProductoDto masBarato = productos.get(0);
        ProductoDto masCostoso = productos.get(size - 1);
        return List.of(masBarato, masCostoso);

    }


    private List<ProductoDto> generarRecomendacionRating(List<ProductoDto> productos) {
        productos = productos.stream().sorted((o1, o2) -> o1.getRating().compareTo(o2.getRating())).toList();
        int size = productos.size();
        ProductoDto menosRating = productos.get(0);
        ProductoDto masRating  = productos.get(size - 1);
        return List.of(menosRating, masRating);

    }

}
