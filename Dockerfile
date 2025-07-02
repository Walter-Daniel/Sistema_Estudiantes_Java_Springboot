FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/school-0.0.1.jar
COPY ${JAR_FILE} app_school.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_school.jar"]