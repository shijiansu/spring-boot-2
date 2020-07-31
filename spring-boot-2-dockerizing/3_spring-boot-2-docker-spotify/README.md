
- https://github.com/spotify/dockerfile-maven
- https://github.com/spotify/dockerfile-maven/blob/master/docs/usage.md

Build each layer into docker image

# Automation scripts

```shell script
run-test.sh # test for application
run.sh # springboot run for application through Idea
run-stop.sh # stop for application

run-package.sh # package artifact and prepare for classes 
run-docker-build-by-plugin.sh # build to local image by plugin
run-docker-build-test.sh # test docker build

run-docker.sh # start docker
run-docker-debug.sh # start docker with java debug - Idea -> Run -> Edit Configurations -> Remote
run-docker-stop.sh # stop docker

# dockerfile:build	Builds a Docker image from a Dockerfile.
# dockerfile:tag	Tags a Docker image.
# dockerfile:push	Pushes a Docker image to a repository.
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

# One stop script

```shell script
/bin/bash run.sh
/bin/bash run-test.sh
/bin/bash run-stop.sh
```
