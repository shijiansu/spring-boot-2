#!/bin/bash
if ! /bin/bash run-package.sh; then
  echo "[RUN DOCKER] Run package failed" && exit 1
fi
source env.sh
# https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#container-closure
./gradlew jib \
  --image="$IMAGE_NAME" \
  -Djib.container.environment=DEPENDENCY="$PACKAGE_FOLDER/dependency" \
  -Djib.container.creationTime="$(date +%Y-%m-%dT%H:%M:%S)+08:00"
