#!/bin/bash
if ! /bin/bash run-package.sh; then
  echo "[RUN DOCKER] Run package failed" && exit 1
fi
source env.sh
docker build --build-arg DEPENDENCY="${PACKAGE_FOLDER}/dependency" -t "${IMAGE_NAME}" .
