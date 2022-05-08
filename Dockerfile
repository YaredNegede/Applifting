FROM adoptopenjdk/openjdk11

LABEL maintainer="Yared Negede"

WORKDIR /applifting

COPY ./target/Applifting-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "Applifting-0.0.1-SNAPSHOT.jar"]