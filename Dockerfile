FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
RUN mkdir -p /enrollservice/app
ENV PROJECT_HOME /enrollservice/app
COPY build/libs/enroll-0.0.1-SNAPSHOT.jar $PROJECT_HOME/app.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://db:27017/enrollee","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
