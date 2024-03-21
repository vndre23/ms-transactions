#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-slim as build

VOLUME /tmp

# se agregar el jar del microservicio al contenedor
COPY target/ms-transactions-0.0.1-SNAPSHOT.jar /ms-transactions-0.0.1-SNAPSHOT.jar

ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null

#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/ms-config-server-0.0.1-SNAPSHOT.jar"]