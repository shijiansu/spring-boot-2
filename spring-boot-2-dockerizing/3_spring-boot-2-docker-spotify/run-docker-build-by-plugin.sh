#!/bin/bash
if ! /bin/bash run-package.sh; then
  echo "[RUN DOCKER] Run package failed" && exit 1
fi
source env.sh
# https://github.com/spotify/dockerfile-maven
## how to pass value to maven plugin - https://github.com/spotify/dockerfile-maven/blob/master/docs/usage.md
./mvnw dockerfile:build \
  -Ddockerfile.repository="${IMAGE_NAME_PREFIX}/${DOCKER_NAME}" \
  -Ddockerfile.tag="${VERSION}"
