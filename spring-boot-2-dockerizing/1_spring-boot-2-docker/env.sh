#!/bin/bash
export ARTIFACT_ID="spring-boot-2-docker"
export VERSION="0.0.1-SNAPSHOT"
export DOCKER_NAME="${ARTIFACT_ID}"
export IMAGE_NAME_PREFIX="shijiansu" # shijiansu is user name of hub.docker.com
export IMAGE_NAME="${IMAGE_NAME_PREFIX}/${DOCKER_NAME}:${VERSION}" # image name
export PACKAGE_FOLDER="target" # package folder - maven project
export LIB_FOLDER="" # if there is lib folder - maven project
