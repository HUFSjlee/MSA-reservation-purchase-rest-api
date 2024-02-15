FROM openjdk:18-jdk-slim

WORKDIR /app/userservice

COPY ../build/libs/user-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]