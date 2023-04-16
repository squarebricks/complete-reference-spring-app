FROM openjdk:17-alpine
ADD target/complete-reference-spring-app-1.0.0.jar complete-reference-spring-app-1.0.0.jar
EXPOSE 8443

ENTRYPOINT ["java", "-jar", "complete-reference-spring-app-1.0.0.jar"]
