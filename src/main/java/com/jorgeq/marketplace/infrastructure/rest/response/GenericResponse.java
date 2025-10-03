package com.jorgeq.marketplace.infrastructure.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericResponse {
    private int codigo;
    private String mensaje;
    private Object data;
}
