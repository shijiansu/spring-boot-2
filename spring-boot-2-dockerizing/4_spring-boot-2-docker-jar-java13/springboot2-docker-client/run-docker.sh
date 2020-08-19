#!/bin/bash
/bin/bash run-docker-build.sh
/bin/bash run-docker-stop.sh

docker container run -d --rm \
  --hostname springboot2-docker-client \
  --network=springboot2-docker-network \
  -p 8081:8081 --name springboot2-docker-client shijian/springboot2-docker-client:latest

sleep 8s

# application log
# docker logs -f springboot2-docker-client
docker logs springboot2-docker-client

# test the api
curl localhost:8081/
echo ""
curl localhost:8081/service

echo ""
echo ""
