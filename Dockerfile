FROM adoptopenjdk/openjdk11
EXPOSE 9090
ARG JAR_FILE=target/SpringBootRestJDBC-1.0.0.jar
ADD ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]

# commands
# docker build -t appname .
# docker image ls
# docker run -p8080:8080 ***