FROM openjdk:18-jdk-slim

WORKDIR /app/stockservice

COPY ../build/libs/stock-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "stock-service-0.0.1-SNAPSHOT.jar"]