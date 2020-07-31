#!/bin/bash
ARTIFACT_ID="spring-boot-2-docker-gradle"
export ARTIFACT_ID

VERSION="0.0.1-SNAPSHOT"
export VERSION

# docker name
DOCKER_NAME="${ARTIFACT_ID}"
export DOCKER_NAME

## shijiansu is user name of hub.docker.com
IMAGE_NAME_PREFIX="shijiansu"
export IMAGE_NAME_PREFIX

# image name
IMAGE_NAME="${IMAGE_NAME_PREFIX}/${DOCKER_NAME}:${VERSION}"
export IMAGE_NAME

# package folder
if [[ -f pom.xml ]]; then
  PACKAGE_FOLDER="target" # maven project
elif [[ -f build.gradle ]]; then
  PACKAGE_FOLDER="build" # gradle project
else
  PACKAGE_FOLDER="target" # deafult case
fi
export PACKAGE_FOLDER

# if there is lib folder
if [[ -f pom.xml ]]; then
  LIB_FOLDER="" # maven project
elif [[ -f build.gradle ]]; then
  LIB_FOLDER="libs/" # gradle project
else
  LIB_FOLDER="" # deafult case
fi
export LIB_FOLDER
