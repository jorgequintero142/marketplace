package com.jorgeq.marketplace.infrastructure.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeq.marketplace.domain.model.ComparacionDto;
import com.jorgeq.marketplace.domain.model.ProductoDto;
import com.jorgeq.marketplace.infrastructure.rest.request.ComparacionRequest;
import com.jorgeq.marketplace.infrastructure.rest.response.GenericResponse;
import com.jorgeq.marketplace.infrastructure.rest.swagger.SwaggerAnnotations;
import com.jorgeq.marketplace.infrastructure.rest.swagger.SwaggerExamples;
import com.jorgeq.marketplace.usecases.CompararProductosUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/comparador-productos")
public class ComparadorProductosController {

    private final CompararProductosUseCase compararProductosUseCase;

    public ComparadorProductosController(CompararProductosUseCase compararProductosUseCase) {
        this.compararProductosUseCase = compararProductosUseCase;
    }

    @Operation(summary = "Compara una lista de productos. Devuelve una lista con las caracteristicas de los productos y dos recomendaciones mostrando los productos con mayor y menor precio y rating", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de códigos de productos a comparar", required = true, content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.COMPARACION_REQUEST_EXAMPLE))))
    @SwaggerAnnotations.ComparacionApiResponses
    @ApiResponse(responseCode = "200", description = "Comparación realizada", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.COMPARACION_SUCCESS_EXAMPLE)))
    @ApiResponse(responseCode = "400", description = "Error con el parámetro de búsqueda", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.BAD_REQUEST_EXAMPLE)))
    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.NOT_FOUND_EXAMPLE)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.INTERNAL_SERVER_ERROR_EXAMPLE)))
    @PostMapping()
    public ResponseEntity<GenericResponse> compararProductos(@RequestBody ComparacionRequest payload) {
        ComparacionDto comparacion = compararProductosUseCase.compararProductos(payload.getCodigosProductos());
        GenericResponse genericResponse = GenericResponse.builder()
                .codigo(HttpStatus.OK.value())
                .mensaje("Comparación realizada")
                .data(comparacion)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @Operation(summary = "Compara productos por criterio con orden configurable", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de códigos de productos a comparar", required = true, content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.COMPARACION_REQUEST_EXAMPLE))))
    @SwaggerAnnotations.ComparacionCriterioApiResponses
    @ApiResponse(responseCode = "200", description = "Comparación por criterio realizada", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.COMPARACION_CRITERIO_SUCCESS_EXAMPLE)))
    @ApiResponse(responseCode = "400", description = "Criterio inválido", content = @Content(mediaType = "application/json", schema = @Schema(example = SwaggerExamples.CRITERIO_INVALIDO_EXAMPLE)))
    @PostMapping("/{criterio}")
    public ResponseEntity<GenericResponse> compararProductosPorCriterio(
            @PathVariable String criterio,
            @RequestBody ComparacionRequest payload,
            @RequestParam(defaultValue = "asc") String orden) {

        boolean ascendente = "asc".equalsIgnoreCase(orden);
        List<ProductoDto> comparacion = compararProductosUseCase.compararProductosPorCriterio(
                criterio, payload.getCodigosProductos(), ascendente);

        GenericResponse genericResponse = GenericResponse.builder()
                .codigo(HttpStatus.OK.value())
                .data(comparacion)
                .mensaje(String.format("Comparacion por criterio %s con orden %s",
                        criterio, ascendente ? "asc" : "desc"))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
}
