package com.jorgeq.marketplace.usercases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeq.marketplace.domain.gateways.ComparadorProductosRepository;
import com.jorgeq.marketplace.domain.model.ComparacionDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompararProductosUseCase {
    private final ComparadorProductosRepository comparadorProductosRepository;

    public ComparacionDto compararProductos(List<String> codigosProductos) {
       // ComparacionDto comparacionDto = ComparacionDto.builder().build();

        return comparadorProductosRepository.compararProductos(codigosProductos);
        
    }



}
