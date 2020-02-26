FROM openjdk:8-alpine
WORKDIR /app
COPY build/libs/swagger4kafka-integration-tests-*.jar app/app.jar
ENTRYPOINT ["java", "-jar", "app/app.jar"]
EXPOSE 8080
