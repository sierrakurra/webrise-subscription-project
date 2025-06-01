FROM azul/zulu-openjdk:17.0.0-17.28.13-jre
WORKDIR /app
MAINTAINER vlidzhigoryaev@digital-spirit.ru
COPY ./build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]