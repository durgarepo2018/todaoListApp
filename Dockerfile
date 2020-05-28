FROM openjdk:12-alpine

COPY target/todoListApp-*.jar  /todoListApp.jar

CMD ["java", "-jar", "todoListApp.jar"]