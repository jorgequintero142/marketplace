# marketplace
Desarrollo hecho con Spring Boot, usando Clean Architecture. El modulo desarrollado corresponde a un comparador de productos. Se decidió nombrar el proyecto como "marketplace", para en un futuro poder agregar otros módulos.

## Lista endpoints
Se desarrollaron dos endpoints tipo REST, para realizar comparaciones. Ambos necesitan una lista con los códigos de los producto a comparar. El verbo utilizado fue el POST esto para aprovarchar el Body y poder enviar, según una regla de negocio que se propone, entre 2 y 6 códigos de producto.

La respuesta en ambos casos es un mensaje indicando el exito de la operacion, un código que corresponde con la respuesta HTTP, y la data, con la estructura de la comparación. Existen dos comparaciones, la primera con todos los productos, y dos recomendaciones por precio y rating. La segunda, recibe un criterio a comparar, y un orden, opcional.

`/api/v1/comparador-productos` : Solo necesita la lista con los códigos de productos a comparar.

`/api/v1/comparador-productos/{criterio}`: Necesita la lista con los códigos de productos a comparar, y un criterio (descripcion, rating, precio, especificaciones).


Tambien se obtiene, en caso de error, una respuesta amigable para el usario, y el detalle del error en los logs de la aplicación.

## Configuración

* Para poder ejecutar la aplicación es necesario tener instalado en su equipo maven y docker.
* Para un paso a paso detallado de la instalación, revise el archivo
* Para ver más detalles del request  y response remitirse a la documentación de swagger.

## Estrategia técnica
La arquitectura que se implementó fue Clean Arquitecture; así, con un proyecto básico de spring boot con maven y el spring-boot-starter-web, se creó una estructura de paquetes, en la que las entidades y los casos de uso, fueran el nivel más bajo, y según el desarrollo de la solución fue creaciendo, se empezó a utilizar otras capas en paquetes como infraestructure.Todo esto para poder desarrollar una aplicación mantenible con el tiempo, centrada en la lógica de negocio.

Respecto a la "pureza" del dominio, se consideró usar Lombok, para aproverchar el potencial de la librería al momento de trabajar con Dto's. Se diseñaron dos endpoints, para realizar comparaciones. El verbo utilizado fue el POST, esto para aprovarchar el Body y poder enviar, hasta 6 codigos de producto. 

La respuesta en ambos casos es un ResponseEntity de un objeto con los atributos mensaje el cual indica el resultado exitoso, un código que corresponde con la respuesta HTTP, y la data, un objeto con la estructura de la comparación según cada enpoint.

Se creó un archivo json con 20 productos de ejemplo; para esto se le indicó a cursor que creará datos de prueba, usando la estructura de la clase ProductoDto.
 
 El control de errores se centralizó usando el GlobalHandler. Se definieron dos tipos de excepciones para enviar mensajes y códigos personalizados, correspondientes al código 400 y 404. Se capturaron otras dos, una excepcion general, y el error con el payload. 
 
 Cómo se sugirió usar GenAI tools, se decidió desarrollar la aplicación usando Cursor. Los prompts enviados se enfocaron en optimizar código, revisión y recomendacion de buenas practicas, y la ayuda solucionando un error de dependencias con algunas librerías. Tambien se solicitó actualizar el archivo de prompts, con el prompt enviado, los archvios usados, y el resultado.

 Se realizaron test unitarios centradose en la lógica de negocio, es decir, se realizaron test sobre el caso de uso.
 Se usaron logs para poder realizar la traza de la petición. 