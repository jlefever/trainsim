FROM openjdk:11
ADD target/*-shaded.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]