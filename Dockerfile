FROM openjdk:8
ADD multi-card-system.jar multi-card-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom", "-jar", "multi-card-system.jar"]