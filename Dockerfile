# Stage 1: Build the application
FROM openjdk:23-jdk-slim AS builder

# Set the working directory
WORKDIR /app

COPY mvnw ./mvnw
COPY mvnw.cmd ./mvnw.cmd
COPY pom.xml ./pom.xml
COPY src ./src

# Install Maven and required build dependencies
RUN apt-get update && \
    apt-get install -y maven git wget && \
    mvn dependency:resolve && \
    mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]