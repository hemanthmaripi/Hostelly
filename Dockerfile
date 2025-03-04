# Step 1: Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file from the host machine to the container
COPY target/*.jar app.jar

# Step 4: Expose the application port (same as your Spring Boot server.port)
EXPOSE 8080

# Step 5: Run the application
CMD ["java", "-jar", "app.jar"]
