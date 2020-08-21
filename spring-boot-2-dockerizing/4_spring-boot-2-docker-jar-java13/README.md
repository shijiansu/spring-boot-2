# Automation scripts

```bash
run-test.sh # test spring boot application
run.sh # run spring boot application

run-docker-build.sh # build docker image
run-docker.sh # start docker container
run-docker-stop.sh # stop docker container

run-docker-all.sh # start docker containers <- module - Dockerfile
run-docker-all-stop.sh # stop docker containers <- module - Dockerfile

run-docker-build-all.sh # build docker images

run-docker-compose.sh # start docker compose <- docker-compose.yml
run-docker-compose-down.sh # stop docker compose <- docker-compose.yml

run-test-all.sh # test apis

run-cleanup-all.sh # clean up all testing resources in this example
```

# Testing

```bash
run-docker-compose.sh
docker logs springboot2-docker-client
docker exec -it springboot2-docker-client /bin/sh # /bin/sh for alpine
ping springboot2-docker
```

# Architecture

```bash
# 1 - manully run - run as application - because needs manually stop
springboot2-docker/run.sh
springboot2-docker-client/run.sh

# 2 - run as container
run-docker-all.sh
|-springboot2-docker
  |-run-docker-build.sh
  |-run-docker.sh
|-springboot2-docker-client
  |-run-docker-build.sh
  |-run-docker.sh

# 3 - run as container - docker compose
run-docker-compose.sh
|- run-docker-build-all.sh
  |-springboot2-docker/run-docker-build.sh
  |-springboot2-docker-client/run-docker-build.sh
|-docker-compose.yml
|- run-test-all.sh

# clean all resources
run-cleanup-all.sh
|- run-docker-all-stop.sh
|- run-docker-compose-down.sh
```

# Run as application

```bash
springboot2-docker/run.sh
# -> http://localhost:8080/
springboot2-docker-client/run.sh
# -> http://localhost:8081/
# -> http://localhost:8081/service -> localhost:8080
```

# Run as container

```bash
springboot2-docker/run-docker.sh
# -> http://localhost:8080/ -> springboot2-docker container 8080
springboot2-docker-client/run-docker.sh
# -> http://localhost:8081/ -> springboot2-docker-client container 8081
# -> http://localhost:8081/service
#   -> springboot2-docker-client container 8081/service
#   -> springboot2-docker-network
#   -> springboot2-docker container 8080 via http://springboot2-docker:8080/ (in the same docker network, hit the service by hostname)

# in the application-docker.properites, it changes the endpoint from localhost to docker hostname.
# it runs the spring boot profile in the Dockerfile, entrypoint command.
```

# Run as container - Docker compose

```bash
run-docker-compose.sh
# -> http://localhost:8080/ -> springboot2-docker container 8080
# -> http://localhost:8081/ -> springboot2-docker-client container 8081
# -> http://localhost:8081/service
#   -> springboot2-docker-client container 8081/service
#   -> springboot2-docker-network
#   -> springboot2-docker container 8080
```
