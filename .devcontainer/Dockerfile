# Base image with Java 17
FROM mcr.microsoft.com/devcontainers/java:17

# Install OpenJDK 17 explicitly
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install Maven
RUN apt-get install -y maven

# Install Node.js & npm (for React frontend if needed)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs

# Set JAVA_HOME explicitly
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
