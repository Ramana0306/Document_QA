# =========================
# 1. Build React frontend
# =========================
FROM node:22 AS frontend-build

WORKDIR /frontend

COPY frontend/package*.json ./

RUN npm install

COPY frontend/ ./

RUN npm run build


# =========================
# 2. Build Spring Boot backend
# =========================
FROM maven:3.9-eclipse-temurin-21 AS backend-build

WORKDIR /backend

COPY document-qa/pom.xml .

RUN mvn dependency:go-offline -B

COPY document-qa/src ./src

RUN mvn clean package -DskipTests


# =========================
# 3. Final single container
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Install Nginx
RUN apt-get update \
    && apt-get install -y nginx \
    && rm -rf /var/lib/apt/lists/*

# Copy React build
COPY --from=frontend-build /frontend/dist /usr/share/nginx/html

# Copy Spring Boot JAR
COPY --from=backend-build /backend/target/*.jar /app/app.jar

# Copy Nginx configuration
COPY document-qa/nginx.conf /etc/nginx/sites-available/default

# Start Nginx + Spring Boot
RUN printf '#!/bin/sh\nnginx\nexec java -jar /app/app.jar\n' > /app/start.sh \
    && chmod +x /app/start.sh

EXPOSE 10000

CMD ["/app/start.sh"]