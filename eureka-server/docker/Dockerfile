FROM openjdk:18-jdk-slim

WORKDIR /app/eurekeserver

COPY ../build/libs/eureka-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]