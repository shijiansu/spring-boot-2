#!/bin/bash
if ! /bin/bash run-package.sh; then
  echo "[RUN DOCKER] Run package failed" && exit 1
fi
source env.sh
# https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin
./mvnw com.google.cloud.tools:jib-maven-plugin:dockerBuild \
  -Dimage="$IMAGE_NAME" \
  -Djib.container.environment=DEPENDENCY="$PACKAGE_FOLDER/dependency" \
  -Djib.container.creationTime="$(date +%Y-%m-%dT%H:%M:%S)+08:00"
## default to take Base image 'gcr.io/distroless/java:8'
## -Djib.from.image="openjdk:8-jdk-alpine"
