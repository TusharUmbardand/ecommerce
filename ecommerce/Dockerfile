# Step 1: Use OpenJDK as base image
FROM openjdk:17-jdk-slim as builder

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Step 4: Download dependencies
RUN ./mvnw dependency:go-offline

# Step 5: Copy source code and build the app
COPY src src
RUN ./mvnw clean package -DskipTests

# Step 6: Run stage (lightweight image)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy JAR file from builder stage
COPY --from=builder /app/target/*.jar eEcommerceApplication.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "eEcommerceApplication.jar"]
