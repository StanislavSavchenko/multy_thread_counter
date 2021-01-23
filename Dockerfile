FROM openjdk:11

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/app.jar"]
