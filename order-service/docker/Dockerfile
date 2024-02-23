FROM openjdk:18-jdk-slim

WORKDIR /app/orderservice

COPY ../build/libs/order-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]