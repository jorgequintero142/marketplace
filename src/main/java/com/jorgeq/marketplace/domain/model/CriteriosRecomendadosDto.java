package com.jorgeq.marketplace.domain.model;

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
public class CriteriosRecomendadosDto {
   private CriterioPrecioDto masEconomico;
   private CriterioPrecioDto masCostoso;
   private CriterioRankingDto masRating;
   private CriterioRankingDto menosRating;
}
