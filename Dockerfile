FROM openjdk:21-alpine AS builder
WORKDIR /app
COPY pom.xml ./
RUN apk add --no-cache maven
RUN mvn compile package
FROM openjdk:21-alpine
WORKDIR /app
COPY target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
