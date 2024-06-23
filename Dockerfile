FROM amazoncorretto:17-alpine-jdk
MAINTAINER ELI
COPY target/DovitBack-0.0.1-SNAPSHOT.jar DovitBackend.jar
ENTRYPOINT ["java", "-jar", "/DovitBackend.jar"]