FROM gradle:8-jdk21 AS build

WORKDIR /app

COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon

FROM eclipse-temurin:21

COPY --from=build /app/build/libs/githubViewer-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

#run command
#docker build -t github-viewer .
#docker run -d --name githubc -p 8080:8080 github-viewer