# Base image with Java 17 (includes OpenJDK 17)
FROM mcr.microsoft.com/devcontainers/java:17

# Install Maven
RUN apt-get update && apt-get install -y maven

# Install Node.js & npm (for React frontend if needed)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs

# Set JAVA_HOME explicitly so mvnw can find Java
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
