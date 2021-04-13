#!/bin/bash
export ARTIFACT_ID="spring-boot-2-docker"
export VERSION="0.0.1-SNAPSHOT"
export DOCKER_NAME="${ARTIFACT_ID}"
export IMAGE_NAME_PREFIX="shijiansu" # shijiansu is user name of hub.docker.com
export IMAGE_NAME="${IMAGE_NAME_PREFIX}/${DOCKER_NAME}:${VERSION}" # image name

# package folder
if [[ -f pom.xml ]]; then
  PACKAGE_FOLDER="target" # maven project
elif [[ -f build.gradle ]]; then
  PACKAGE_FOLDER="build" # gradle project
else
  PACKAGE_FOLDER="target" # default case
fi
export PACKAGE_FOLDER

# if there is lib folder
if [[ -f pom.xml ]]; then
  LIB_FOLDER="" # maven project
elif [[ -f build.gradle ]]; then
  LIB_FOLDER="libs/" # gradle project
else
  LIB_FOLDER="" # default
fi
export LIB_FOLDER
