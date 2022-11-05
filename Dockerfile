FROM openjdk:11-slim as build
MAINTAINER alok.sachan.83@gmail.com
COPY target/accounts-service-0.0.1-SNAPSHOT.jar accounts-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/accounts-service-0.0.1-SNAPSHOT.jar"]