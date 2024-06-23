FROM amazoncorretto:17-alpine-jdk
MAINTAINER ELI
COPY target/DovitBack-0.0.1-SNAPSHOT.jar DovitBack.jar
ENTRYPOINT ["java", "-jar", "/DovitBack.jar"]