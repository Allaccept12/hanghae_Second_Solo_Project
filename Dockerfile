FROM java:8
EXPOSE 8081
ARG JAR_FILE=build/libs/*.jar
ARG PROPERTIES_FILE=src/main/resources/application.yml

COPY ${JAR_FILE} app.jar
COPY ${PROPERTIES_FILE} application-set1.yml

ENTRYPOINT ["java", "-jar", "/app.jar"]