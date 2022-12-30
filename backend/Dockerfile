FROM openjdk:17-jdk-alpine

COPY . /project
RUN  cd /project && ./gradlew bootJar

#run the spring boot application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dblabla", "-jar","/project/build/libs/spring-boot-0.0.1.jar"]