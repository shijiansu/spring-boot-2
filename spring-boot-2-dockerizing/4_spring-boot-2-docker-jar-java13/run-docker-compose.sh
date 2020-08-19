#!/bin/bash
/bin/bash run-docker-compose-down.sh

cd springboot2-docker || exit 1
/bin/bash run-docker-build.sh
cd ..

cd springboot2-docker-client || exit 1
/bin/bash run-docker-build.sh
cd ..

docker-compose up -d --remove-orphans
docker-compose ps

# wait until the spring boot fully started
# you can provide the healthcheck + wait-for-it.sh https://docs.docker.com/compose/startup-order/
sleep 10s

curl http://localhost:8080/
echo ""
curl http://localhost:8081/
echo ""
curl http://localhost:8081/service
echo ""
