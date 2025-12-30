# Base image with Java 17
FROM mcr.microsoft.com/devcontainers/java:17

# Install Maven
RUN apt-get update && apt-get install -y maven

# Install Node.js & npm (for React frontend if needed)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs

# Optional: MySQL client (for testing external AWS RDS connection)
RUN apt-get install -y mysql-client
