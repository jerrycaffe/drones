FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/drones-0.0.1-SNAPSHOT.jar /app/drones.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "drones.jar"]