package com.jorgeq.marketplace.domain.exceptions;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String codigo) {
        super("Producto con código " + codigo + " no encontrado");
    }
}