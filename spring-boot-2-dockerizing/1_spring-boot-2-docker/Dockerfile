FROM openjdk:8-jdk-alpine
# create application user
## cutomized logic of application.pid becuase writing pid file
RUN addgroup -S spring && adduser -S spring -G spring \
    && touch application.pid \
    && chown spring:spring application.pid
USER spring:spring
# copy dependencies
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# run main
ENTRYPOINT ["java","-cp","app:app/lib/*","spring.boot._2.docker.Application"]
