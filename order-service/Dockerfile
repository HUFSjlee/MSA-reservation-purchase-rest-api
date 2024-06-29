FROM openjdk:18-jdk-slim

COPY /order-service/build/libs/order-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]
