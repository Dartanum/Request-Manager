#Base image
FROM amazoncorretto:17.0.4
#Application's jar file location
ARG JAR_FILE=target/*.jar
#Copy jar file from current machine to container
COPY ${JAR_FILE} application.jar
EXPOSE 8080
#Command to run container
ENTRYPOINT ["java", "-jar", "/application.jar"]