package com.jorgeq.marketplace.infrastructure.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeq.marketplace.domain.model.ComparacionDto;
import com.jorgeq.marketplace.usercases.CompararProductosUseCase;

@RestController
@RequestMapping("/comparador-productos")
public class ComparadorProductosController {

    private final CompararProductosUseCase compararProductosUseCase;

    public ComparadorProductosController(CompararProductosUseCase compararProductosUseCase) {
        this.compararProductosUseCase = compararProductosUseCase;
    }


    @PostMapping("/comparar")
    public ComparacionDto compararProductos(@RequestBody List<String> codigosProductos) {
        return compararProductosUseCase.compararProductos(codigosProductos);
    }
}
   
