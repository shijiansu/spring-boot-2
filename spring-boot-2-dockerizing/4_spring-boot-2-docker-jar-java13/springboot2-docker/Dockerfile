FROM openjdk:13-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring \
  && mkdir /app && chown spring:spring /app
USER spring
COPY --chown=spring:spring target/springboot2-docker.jar /app
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/springboot2-docker.jar"]
