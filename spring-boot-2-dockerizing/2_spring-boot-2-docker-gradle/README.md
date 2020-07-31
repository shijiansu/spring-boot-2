
- https://spring.io/guides/gs/spring-boot-docker/
- https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#container-closure

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

# Setup Gradle wrapper

- https://docs.gradle.org/current/userguide/gradle_wrapper.html
- https://docs.gradle.org/current/userguide/kotlin_dsl.html

```shell script
gradle wrapper --gradle-version 6.3 --distribution-type all
# create pom.xml with completed configuration, when execute "./gradlew init"
# will convert maven to gradle settings
./gradlew init --type pom.xml
# combine the example from https://start.spring.io/
```

# Script step by step

```shell script
./mvnw clean spring-boot:run
```

# One stop script

```shell script
/bin/bash run.sh
/bin/bash run-test.sh
/bin/bash run-stop.sh
```
