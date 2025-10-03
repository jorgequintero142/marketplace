package com.jorgeq.marketplace.infrastructure.rest.swagger;

/**
 * Clase centralizada para ejemplos de Swagger/OpenAPI
 * Facilita el mantenimiento y reutilización de ejemplos
 */
public class SwaggerExamples {

    // ========== REQUEST EXAMPLES ==========
    
    public static final String COMPARACION_REQUEST_EXAMPLE = """
            {
              "codigosProductos": [
                "P015",
                "P011"
              ]
            }
            """;

    // ========== RESPONSE EXAMPLES ==========
    
    public static final String COMPARACION_SUCCESS_EXAMPLE = """
            {
              "codigo": 200,
              "mensaje": "Comparación realizada",
              "data": {
                "productos": [
                  {
                    "id": "P001",
                    "nombre": "Auriculares Inalámbricos X1",
                    "imagenUrl": "https://example.com/img/auriculares-x1.jpg",
                    "descripcion": "Auriculares Bluetooth con cancelación de ruido",
                    "precio": 59.99,
                    "rating": 4.5,
                    "especificaciones": "Bluetooth 5.3; 35h batería; USB-C"
                  },
                  {
                    "id": "P002",
                    "nombre": "Teclado Mecánico Pro",
                    "imagenUrl": "https://example.com/img/teclado-pro.jpg",
                    "descripcion": "Teclado mecánico switch rojo con retroiluminación RGB",
                    "precio": 89.9,
                    "rating": 4.7,
                    "especificaciones": "Switch rojo; 104 teclas; RGB; cable USB-C"
                  }
                ],
                "criteriosRecomendados": {
                  "masEconomico": {
                    "nombre": "Auriculares Inalámbricos X1",
                    "precio": 59.99
                  },
                  "masCostoso": {
                    "nombre": "Teclado Mecánico Pro",
                    "precio": 89.9
                  },
                  "masRating": {
                    "nombre": "Teclado Mecánico Pro",
                    "ranking": 4.7
                  },
                  "menosRating": {
                    "nombre": "Auriculares Inalámbricos X1",
                    "ranking": 4.5
                  }
                }
              }
            }
            """;

    public static final String COMPARACION_CRITERIO_SUCCESS_EXAMPLE = """
            {
              "codigo": 200,
              "mensaje": "Comparacion por criterio rating con orden asc",
              "data": [
                {
                  "id": "P015",
                  "nombre": "Tablet 10'' 128GB",
                  "imagenUrl": "https://example.com/img/tablet-10-128.jpg",
                  "descripcion": "Tablet ligera con pantalla Full HD",
                  "precio": 199.0,
                  "rating": 4.0,
                  "especificaciones": "10.1''; 128GB; 4GB RAM"
                },
                {
                  "id": "P011",
                  "nombre": "Barra de Sonido 2.1",
                  "imagenUrl": "https://example.com/img/soundbar-21.jpg",
                  "descripcion": "Barra con subwoofer inalámbrico",
                  "precio": 199.99,
                  "rating": 4.3,
                  "especificaciones": "2.1; Bluetooth; HDMI ARC"
                }
              ]
            }
            """;

    // ========== ERROR EXAMPLES ==========
    
    public static final String BAD_REQUEST_EXAMPLE = """
            {
              "error": "Error de parámetro",
              "timestamp": "2025-10-02T22:17:49.0568009",
              "detalle": "No se ha enviado ningún código de producto",
              "status": 400
            }
            """;

    public static final String CRITERIO_INVALIDO_EXAMPLE = """
            {
              "error": "Error de parámetro",
              "timestamp": "2025-10-02T22:21:35.1842813",
              "detalle": "Criterio de búsqueda no permitido",
              "status": 400
            }
            """;

    public static final String NOT_FOUND_EXAMPLE = """
            {
              "error": "Producto no encontrado",
              "timestamp": "2025-10-02T22:07:41.9672898",
              "detalle": "Producto con código string, string2 no encontrado",
              "status": 404
            }
            """;

    public static final String INTERNAL_SERVER_ERROR_EXAMPLE = """
            {
              "error": "Error interno",
              "timestamp": "2025-10-02T22:20:29.6092214",
              "detalle": "Error interno",
              "status": 500
            }
            """;
}
