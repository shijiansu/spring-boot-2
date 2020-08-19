# How to start

- <https://github.com/spotify/dockerfile-maven>
- <https://github.com/spotify/dockerfile-maven/blob/master/docs/usage.md>

Build each layer into docker image

# Automation scripts

```bash
env.sh # environment setup for this example

# application
run-test.sh # test spring boot application
run.sh # run spring boot application
run-stop.sh # stop spring boot application

# build
run-package.sh # package artifact and prepare for classes files
run-docker-build-test.sh # build docker image build

# plugin
run-docker-build-by-plugin.sh # build docker image

# docker
run-docker.sh # start docker container
run-docker-debug.sh # start docker with java debug enabling - Idea -> Run -> Edit Configurations -> Remote
run-docker-stop.sh # stop docker container

# dockerfile:build	Builds a Docker image from a Dockerfile.
# dockerfile:tag	Tags a Docker image.
# dockerfile:push	Pushes a Docker image to a repository.
```

# Architecture

```bash
run-docker-build-by-plugin.sh
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

# Setup Maven wrapper

- https://github.com/takari/maven-wrapper

`mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.5.4`

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
