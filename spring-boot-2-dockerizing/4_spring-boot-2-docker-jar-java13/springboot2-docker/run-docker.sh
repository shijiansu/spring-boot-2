#!/bin/bash
/bin/bash run-docker-build.sh
/bin/bash run-docker-stop.sh

# refer to ../run-all.sh
# docker network create springboot2-docker-network

# POC: try to login and see the settings, override the entrypoint command
#docker container run -it --rm \
#  --hostname springboot2-docker \
#  --entrypoint /bin/sh \
#  --network=springboot2-docker-network \
#  -p 8080:8080 --name springboot2-docker shijian/springboot2-docker:latest

docker container run -d --rm \
  --hostname springboot2-docker \
  --network=springboot2-docker-network \
  -p 8080:8080 --name springboot2-docker shijian/springboot2-docker:latest

sleep 8s

# application log
# docker logs -f springboot2-docker
docker logs springboot2-docker

# test the api
curl localhost:8080/

echo ""
echo ""
