FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
VOLUME ["/repository.txt"]
ENTRYPOINT ["java","-jar","/app.jar"]