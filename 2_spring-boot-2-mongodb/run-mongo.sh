#!/bin/bash
/bin/bash run-mongo-stop.sh
# need to update the syntax from docker-compose to docker stack
docker-compose -f stack.yml up --remove-orphans &
