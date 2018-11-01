FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./bulk_fhir_server/target/bulk_fhir_server*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]