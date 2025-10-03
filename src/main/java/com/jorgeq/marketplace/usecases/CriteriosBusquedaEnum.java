package com.jorgeq.marketplace.usecases;

import java.util.Arrays;

public enum CriteriosBusquedaEnum {

    DESCRIPCION("descripcion"),
    PRECIO("precio"),
    RATING("rating"),
    ESPECIFICACIONES("especificaciones");

    private final String value;

    CriteriosBusquedaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static boolean isValid(String value) {
        return Arrays.stream(values())
                 .anyMatch(criterio -> criterio.value.equalsIgnoreCase(value));
    }
}
