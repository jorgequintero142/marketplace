package com.jorgeq.marketplace.domain.model;

public class Constantes {
    private Constantes() {

    }

    public static final int MIN_PRODUCTOS_COMPARAR = 2;
    public static final int MAX_PRODUCTOS_COMPARAR = 6;
    public static final String ERROR_PRODUCTOS_VACIOS = "No se ha enviado ningún código de producto";
    public static final String ERROR_PRODUCTOS_CANTIDAD = "Se requiere entre " + MIN_PRODUCTOS_COMPARAR + " y "
            + MAX_PRODUCTOS_COMPARAR + " códigos de producto";
}
