FROM openjdk:13-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring \
  && mkdir /app && chown spring:spring /app
USER spring
COPY --chown=spring:spring target/springboot2-docker-client.jar /app
EXPOSE 8081

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app/springboot2-docker-client.jar"]
