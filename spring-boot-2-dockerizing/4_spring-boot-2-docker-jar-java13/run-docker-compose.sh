#!/bin/bash
/bin/bash run-docker-build-all.sh
/bin/bash run-docker-compose-down.sh

docker-compose up -d --remove-orphans
docker-compose ps

/bin/bash run-test-all.sh
