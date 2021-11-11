FROM openjdk:11-jdk
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
ENV SECRET_KEY="CHAVE *********************************************"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]