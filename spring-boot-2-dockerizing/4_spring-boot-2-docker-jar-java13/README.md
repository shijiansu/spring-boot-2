# Automation scripts

```bash
run-test.sh # test spring boot application
run.sh # run spring boot application

run-docker-build.sh # build docker image
run-docker.sh # start docker container
run-docker-stop.sh # stop docker container

run-docker-all.sh # start docker containers <- module - Dockerfile
run-docker-all-stop.sh # stop docker containers <- module - Dockerfile

run-docker-compose.sh # start docker compose <- docker-compose.yml
run-docker-compose-down.sh # stop docker compose <- docker-compose.yml

run-cleanup-all.sh # clean up all testing resources in this example
```

# Architecture

```bash
# manully run - run as application - because needs manually stop
springboot2-docker/run.sh
springboot2-docker-client/run.sh

# run as container
run-docker-all.sh
|-springboot2-docker
  |-run-docker-build.sh
  |-run-docker.sh
|-springboot2-docker-client
  |-run-docker-build.sh
  |-run-docker.sh

# run as container - docker compose
run-docker-compose.sh
|-springboot2-docker
  |-run-docker-build.sh
|-springboot2-docker-client
  |-run-docker-build.sh
|-docker-compose.yml
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
