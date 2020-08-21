#!/bin/bash

# wait until the spring boot fully started.
# you can provide the healthcheck + wait-for-it.sh https://docs.docker.com/compose/startup-order/.
# here use the homemade solutions
waited=0
until curl -s http://localhost:8081/actuator/health | grep -q "UP"; do
  echo "Microservice is unavailable - sleeping"
  sleep 1
  waited=$((waited + 1))
  if [[ ${waited} -gt 60 ]]; then
    echo "Microservice is unavailable - time out, exiting"
    exit 1
  fi
done
echo "Microservice is up"

echo ""
# curl http://localhost:8080/ # do not expose 8080 now; only test the 8081, disable this
# echo ""
curl http://localhost:8081/
echo ""
curl http://localhost:8081/service # client -> service
echo ""
