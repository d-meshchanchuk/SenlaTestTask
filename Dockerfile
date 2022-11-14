FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
ARG STORAGE_FILE=repository.txt
COPY ${JAR_FILE} app.jar
COPY ${STORAGE_FILE} repository.txt
ENTRYPOINT ["java","-jar","/app.jar"]