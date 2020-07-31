#!/bin/bash
# [1 - an issue] - Service "rabbitmq" is using volume "/var/lib/rabbitmq" from the previous container.
# [1 - solution] - docker stop springboot2-rabbitmq && docker rm -v -f springboot2-rabbitmq
# docker stop springboot2-rabbitmq
docker-compose -f stack.yml stop
