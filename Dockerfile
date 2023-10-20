FROM openjdk:20

WORKDIR /app

COPY target/BookManagementSystem-0.0.1-SNAPSHOT.jar .

COPY ./src/main/resources/templates ./src/main/resources/templates

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "BookManagementSystem-0.0.1-SNAPSHOT.jar"]
