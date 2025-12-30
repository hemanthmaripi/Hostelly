FROM mcr.microsoft.com/devcontainers/java:17
RUN apt-get update &&
DEBIAN_FRONTEND=noninteractive apt-get install -y maven curl ca-certificates &&
curl -fsSL https://deb.nodesource.com/setup_18.x | bash - &&
apt-get install -y nodejs &&
rm -rf /var/lib/apt/lists/*
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
