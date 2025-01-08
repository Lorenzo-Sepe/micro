
FROM amazoncorretto:21-alpine-jdk

ADD target/ms-discovery.jar ms-discovery.jar

ENTRYPOINT ["java", "-jar", "ms-discovery.jar","--spring.profiles.active=production"]
