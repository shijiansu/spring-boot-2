
- https://spring.io/guides/gs/spring-boot-docker/
- https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin

# Automation scripts

```shell script
run-test.sh # test for application
run.sh # springboot run for application through Idea
run-jar.sh # springboot run for application through jar
run-stop.sh # stop for application

run-package.sh # package artifact and prepare for classes 
run-docker-build.sh # build to local image
run-docker-build-by-plugin.sh # build to local image by plugin
run-docker-build-test.sh # test docker build
run-docker-push-by-plugin.sh # push to docker hub by plugin using local credential

run-docker.sh # start docker
run-docker-debug.sh # start docker with java debug - Idea -> Run -> Edit Configurations -> Remote
run-docker-stop.sh # stop docker
```

# Java development environment

`sdk list java && sdk current java`

# Setup Maven wrapper

- https://github.com/takari/maven-wrapper

`mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.5.4`

# Script step by step

```shell script
./mvnw clean spring-boot:run
```
