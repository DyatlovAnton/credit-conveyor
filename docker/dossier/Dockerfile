FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
COPY fonts fonts
ENTRYPOINT ["java", "-jar", "application.jar"]
