FROM maven:3.6.0-jdk-11-slim AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean
RUN mvn -f /home/app/pom.xml package -Dmaven.test.skip
CMD ["java","-jar","/home/app/target/spring-17-0.0.1-SNAPSHOT.jar"]
