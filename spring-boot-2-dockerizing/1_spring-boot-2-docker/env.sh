#!/bin/bash
ARTIFACT_ID="spring-boot-2-docker"
export ARTIFACT_ID

VERSION="0.0.1-SNAPSHOT"
export VERSION

# docker name
DOCKER_NAME="${ARTIFACT_ID}"
export DOCKER_NAME

# image name
## shijiansu is user name of hub.docker.com
IMAGE_NAME="shijiansu/${DOCKER_NAME}:${VERSION}"
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
  LIB_FOLDER="" # default
fi
export LIB_FOLDER
