#!/bin/bash

/bin/bash springboot2-docker-client/run-docker-stop.sh
/bin/bash springboot2-docker/run-docker-stop.sh

docker network rm springboot2-docker-network
