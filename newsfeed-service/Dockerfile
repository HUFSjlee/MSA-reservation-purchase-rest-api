FROM openjdk:18-jdk-slim

COPY ./newsfeed-service/build/libs/newsfeed-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "newsfeed-service-0.0.1-SNAPSHOT.jar"]
