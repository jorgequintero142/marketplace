package com.jorgeq.marketplace.domain.model;

import java.math.BigDecimal;

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
public class CriterioPrecioDto {
    private String nombre;
    private BigDecimal precio;
}
