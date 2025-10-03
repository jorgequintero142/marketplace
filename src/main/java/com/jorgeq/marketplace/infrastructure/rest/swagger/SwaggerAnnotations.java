package com.jorgeq.marketplace.infrastructure.rest.swagger;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Anotaciones personalizadas para simplificar la documentación Swagger
 */
public class SwaggerAnnotations {

    /**
     * Anotación para respuestas estándar de comparación de productos
     */
    @Target({TYPE, METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comparación realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en los parámetros de entrada"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public @interface ComparacionApiResponses {
    }

    /**
     * Anotación para respuestas de comparación por criterio
     */
    @Target({TYPE, METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comparación por criterio realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Criterio inválido o parámetros incorrectos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public @interface ComparacionCriterioApiResponses {
    }
}
