FROM openjdk:21-jdk

COPY target/*.jar java-app.jar

ENTRYPOINT ["java", "-jar", "/java-app.jar"]