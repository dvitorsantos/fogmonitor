FROM eclipse-temurin:17-jdk-focal

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn install

ENTRYPOINT ["java", "-jar", "/project/target/fogmonitor-1.0-SNAPSHOT.jar"]