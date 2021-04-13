#!/bin/bash
source env.sh
docker images -a | grep "${IMAGE_NAME_PREFIX}/${DOCKER_NAME}" | grep "${VERSION}"
