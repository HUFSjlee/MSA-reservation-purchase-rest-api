FROM openjdk:18-jdk-slim

COPY /api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]
