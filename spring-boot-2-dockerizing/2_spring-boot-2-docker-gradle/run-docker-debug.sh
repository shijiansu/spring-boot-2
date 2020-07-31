#!/bin/bash
/bin/bash run-docker-stop.sh
source env.sh
docker run -e "JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" \
   --build-arg DEPENDENCY="$PACKAGE_FOLDER/dependency" \
   -p 5005:5005 --name "$DOCKER_NAME" -p 8080:8080 -t "$IMAGE_NAME" &
