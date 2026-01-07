FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY build/libs/app.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/app.jar"]