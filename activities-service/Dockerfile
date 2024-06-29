FROM openjdk:18-jdk-slim

#WORKDIR /app/activitiesservice

COPY /activities-service/build/libs/activities-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "activities-service-0.0.1-SNAPSHOT.jar"]
