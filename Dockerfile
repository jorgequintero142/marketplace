FROM eclipse-temurin:17-jdk-alpine
RUN adduser -D jorge
WORKDIR /app
COPY target/*.jar marketplace.jar
EXPOSE 8080
USER jorge
ENTRYPOINT ["java","-jar","marketplace.jar"]