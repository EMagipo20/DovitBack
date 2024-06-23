FROM amazoncorretto:17-alpine-jdk
MAINTAINER ELI
COPY target/DovitBack-0.0.1-SNAPSHOT.jar dovitBackend.jar
ENTRYPOINT ["java", "-jar", "/dovitBackend.jar"]
