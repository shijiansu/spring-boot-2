#!/bin/bash
export ARTIFACT_ID="spring-boot-2-docker-gradle"
export VERSION="0.0.1-SNAPSHOT"
export DOCKER_NAME="${ARTIFACT_ID}"
export IMAGE_NAME_PREFIX="shijiansu" # shijiansu is user name of hub.docker.com
export IMAGE_NAME="${IMAGE_NAME_PREFIX}/${DOCKER_NAME}:${VERSION}" # image name
export PACKAGE_FOLDER="build" # package folder - gradle project
export LIB_FOLDER="libs/" # if there is lib folder - gradle project
