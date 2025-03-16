#FROM ubuntu:latest
#LABEL authors="IBH019"
#
#ENTRYPOINT ["top", "-b"]

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/demo-application.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
