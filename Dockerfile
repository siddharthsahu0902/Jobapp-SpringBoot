FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/jobapp-0.0.1-SNAPSHOT.jar /app/jobApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/jobApp.jar"]