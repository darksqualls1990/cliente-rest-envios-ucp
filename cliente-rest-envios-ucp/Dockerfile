FROM openjdk:8-jdk-alpine

MAINTAINER hector.ramirez@ucp.edu.co

EXPOSE 8080

ARG JAR_FILE=target/cliente-rest-0.0.1.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
