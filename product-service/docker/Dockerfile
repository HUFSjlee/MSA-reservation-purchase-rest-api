FROM openjdk:18-jdk-slim

WORKDIR /app/productservice

COPY ../build/libs/product-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]