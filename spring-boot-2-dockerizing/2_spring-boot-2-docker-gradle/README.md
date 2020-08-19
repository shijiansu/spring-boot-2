# How to start

- <https://spring.io/guides/gs/spring-boot-docker/>
- <https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#container-closure>

# Automation scripts

```bash
env.sh # environment setup for this example

# application
run-test.sh # test spring boot application
run.sh # run spring boot application
run-application-jar.sh # run spring boot application via jar; production way to alternate run.sh
run-stop.sh # stop spring boot application

# build
run-package.sh # package artifact and prepare for classes files
run-docker-build.sh # build docker image
run-docker-build-test.sh # build docker image build

# plugin
run-docker-build-by-plugin.sh # build docker image
run-docker-push-by-plugin.sh # directly push to docker hub using local credential

# docker
run-docker.sh # start docker container
run-docker-debug.sh # start docker with java debug enabling - Idea -> Run -> Edit Configurations -> Remote
run-docker-stop.sh # stop docker container
```

# Architecture

```bash
run-docker-build.sh # or, run-docker-build-by-plugin.sh
|- run-package.sh
# then
run-docker-build-test.sh
# then
run-docker.sh # or, run-docker-debug.sh
# then
run-docker-stop.sh
```

# Java development environment

`sdk list java && sdk current java`

# Setup Gradle wrapper

- https://docs.gradle.org/current/userguide/gradle_wrapper.html
- https://docs.gradle.org/current/userguide/kotlin_dsl.html

```bash
gradle wrapper --gradle-version 6.3 --distribution-type all
# create pom.xml with completed configuration, when execute "./gradlew init"
# will convert maven to gradle settings
./gradlew init --type pom.xml
# combine the example from https://start.spring.io/
```

# Script step by step

```bash
./mvnw clean spring-boot:run
```

# One stop script

```bash
/bin/bash run.sh
/bin/bash run-test.sh
/bin/bash run-stop.sh
```
