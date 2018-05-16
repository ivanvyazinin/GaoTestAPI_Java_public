FROM maven:3.5.3-jdk-8-slim as BUILD
COPY . /data/src/

RUN mvn -f /data/src/pom.xml clean package -DskipTests