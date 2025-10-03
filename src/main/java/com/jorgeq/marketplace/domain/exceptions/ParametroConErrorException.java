package com.jorgeq.marketplace.domain.exceptions;

public class ParametroConErrorException extends RuntimeException {
    public ParametroConErrorException(String mensaje) {
        super(mensaje);
    }
}