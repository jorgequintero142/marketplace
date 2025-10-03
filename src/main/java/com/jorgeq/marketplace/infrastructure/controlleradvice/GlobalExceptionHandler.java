package com.jorgeq.marketplace.infrastructure.controlleradvice;

import com.jorgeq.marketplace.domain.exceptions.ParametroConErrorException;
import com.jorgeq.marketplace.domain.exceptions.ProductoNoEncontradoException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final static String PRODUCTO_NO_ENCONTRADO = "Producto no encontrado";
    private final static String ERROR_PARAMETRO = "Error de parámetro";
    private final static String ERROR_GENERAL = "Error interno";
    private static final String ERROR_PAYLOAD = "El cuerpo de la solicitud es inválido o está mal formado";

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleProductoNoEncontradoException(ProductoNoEncontradoException ex) {
        return buildErrorResponse(PRODUCTO_NO_ENCONTRADO, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParametroConErrorException.class)
    public ResponseEntity<Map<String, Object>> handleParametroConErrorException(ParametroConErrorException ex) {
        return buildErrorResponse(ERROR_PARAMETRO, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error("Error parseando el payload", ex);    
        log.error("mensaje de error : {} ", ex.getMessage());
        return buildErrorResponse(ERROR_PAYLOAD, ERROR_PAYLOAD, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        log.error("Excepción no controlada: ", ex);
        log.error("mensaje de error : {} ", ex.getMessage());
        return buildErrorResponse(ERROR_GENERAL, ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String detalle, HttpStatus status) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("error", error);
        errorBody.put("detalle", detalle);
        errorBody.put("status", status.value());
        return ResponseEntity.status(status).body(errorBody);
    }
}