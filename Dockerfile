FROM openjdk:latest
COPY ./target/semProject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semProject.jar", "db:3306"]