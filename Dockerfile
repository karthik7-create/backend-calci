# ===== Stage 1: Build =====
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy the backend project files
COPY backend/.mvn/ .mvn/
COPY backend/mvnw backend/pom.xml ./

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies first (cached layer)
RUN ./mvnw dependency:resolve -DskipTests

# Copy source code
COPY backend/src/ src/

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# ===== Stage 2: Run =====
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render uses PORT env variable)
EXPOSE 8080

# Run the application
# Render sets the PORT environment variable automatically
ENTRYPOINT ["java", "-jar", "app.jar"]
