#!/bin/bash
/bin/bash run-docker-all-stop.sh

docker network create springboot2-docker-network

cd springboot2-docker || exit 1
/bin/bash run-docker.sh
cd ..

cd springboot2-docker-client || exit 1
/bin/bash run-docker.sh
cd ..
