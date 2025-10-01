package com.jorgeq.marketplace.infrastructure.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comparador-productos")
public class ComparadorProductosController {
    
    @RequestMapping("/ping")
    public String ping() {
        return "Hola Mundo";
    }
}
   
