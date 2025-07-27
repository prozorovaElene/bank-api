FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradlew .
COPY gradle ./gradle/

RUN chmod +x ./gradlew

RUN ./gradlew dependencies --no-daemon

COPY src ./src
RUN ./gradlew build -x test --no-daemon
