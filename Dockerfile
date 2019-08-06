FROM openjdk:8
EXPOSE 8080

COPY ./build/libs /usr/src/app
WORKDIR /usr/src/app

CMD [ "java", "-jar", "myblog-0.0.1-SNAPSHOT.jar"]
