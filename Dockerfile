# Stage 1: Build the application
FROM eclipse-temurin:23-jdk-alpine AS builder

# Set the working directory
WORKDIR /app

COPY mvnw ./mvnw

COPY mvnw.cmd ./mvnw.cmd

COPY pom.xml ./pom.xml

# Download dependencies
RUN ./mvnw dependency:resolve

# Copy the rest of the application source code
COPY src ./src



# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:23-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your Spring Boot app listens on (default: 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]