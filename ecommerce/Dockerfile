# Use official OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper & pom.xml first (for dependency caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (this makes future builds faster)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/ecommerce-0.0.1-SNAPSHOT.jar"]
