FROM gradle:jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/service-recommendation.jar /app/service-recommendation.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/service-recommendation.jar"]