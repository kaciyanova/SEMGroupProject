FROM openjdk:latest
COPY ./target/semProject-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semProject-0.1.0.2-jar-with-dependencies.jar"]