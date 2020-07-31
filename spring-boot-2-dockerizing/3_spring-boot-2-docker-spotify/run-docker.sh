#!/bin/bash
/bin/bash run-docker-stop.sh
source env.sh
docker run --name "$DOCKER_NAME" -p 8080:8080 -t "$IMAGE_NAME" &
