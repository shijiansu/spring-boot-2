#!/bin/bash
source env.sh
# docker kill $(docker ps -q); docker rm $(docker ps -a -q)
docker stop "$DOCKER_NAME" && docker rm "$DOCKER_NAME"
