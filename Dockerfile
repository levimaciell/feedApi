FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

WORKDIR /demo

COPY demo/pom.xml .
COPY demo/src src

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /demo/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT [ "java","-jar", "/app/app.jar" ]