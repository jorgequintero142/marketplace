package com.jorgeq.marketplace.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComparacionDto {
    private List<ProductoDto> productos;
    private CriteriosRecomendadosDto criteriosRecomendados;
}
