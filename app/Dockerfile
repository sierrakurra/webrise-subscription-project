FROM gradle:8.14.1-jdk17 as build
WORKDIR /app
COPY . .
RUN gradle clean bootJar


FROM azul/zulu-openjdk:17.0.0-17.28.13-jre
WORKDIR /app
MAINTAINER vlidzhigoryaev@digital-spirit.ru
COPY --from=build /app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]