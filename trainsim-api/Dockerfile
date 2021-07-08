FROM openjdk:11
ADD target/*-shaded.jar app.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:1044", "-jar", "app.jar"]