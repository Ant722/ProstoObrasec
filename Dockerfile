FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app
COPY . .
RUN mvn clean install -Dmaven.test.skip
FROM openjdk:17-alpine3.14
COPY --from=builder /app/config/target/*0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
CMD ["java", "-jar",  "app.jar"]
