# Stage 1: Build
FROM openjdk:17-jdk-slim AS builder

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml ./
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:23-jre-slim

# Set working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
