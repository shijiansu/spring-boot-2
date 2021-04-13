#!/bin/bash
/bin/bash run-docker-all-stop.sh
/bin/bash run-docker-compose-down.sh

docker image rm shijian/springboot2-docker
docker image rm shijian/springboot2-docker-client
