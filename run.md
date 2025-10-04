# Marketplace instalación
Como se mencioona en README.md, para la instalación y despliegue de la aplicación es necesario tener maven y docker instalados. Git es opcional ya que se puede descargar el proyecto pero se recomienda usarlo. ;)


## Instalación

El proceso de instalación es el siguiente:

### Instrucciones

1. Clone el proyecto desde github. 

`git clone https://github.com/jorgequintero142/marketplace.git`

2. Cambie de directorio.

`cd marketplace`

3. Genere el archivo .jar

`mvn clean package -DskipTests`

4. Cree la imagen de docker

`docker build -t marketplace .`


5. Corra el contenedor

`docker run -d -p 8080:8080  marketplace`
 

 6. Acceda a la documentación de swagger

 `http://localhost:8080/swagger-ui/index.html`
