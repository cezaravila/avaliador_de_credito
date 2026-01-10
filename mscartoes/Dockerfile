FROM maven:3.8-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.12
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG RABBITMQ_SERVER=cursoms-rabbitmq
ARG EUREKA_SERVER=cursoms-container-eureka
ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar