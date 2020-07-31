#!/bin/bash
/bin/bash run-docker-stop.sh
/bin/bash run-docker-build.sh
source env.sh
docker images -a | grep "${IMAGE_NAME_PREFIX}/${DOCKER_NAME}" | grep "${VERSION}"
