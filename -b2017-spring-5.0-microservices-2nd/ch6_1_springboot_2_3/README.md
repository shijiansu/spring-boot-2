
```bash
# Option 1 - manually run the microservice one by one
run-manually.sh

# Option 2 - run or stop all in one
## check if sets of microservices ports are occupied before trigger to start
## write log into _log logging file while booting the microservices
## write processor PID into run.pid after booted the microservices
## stop rabbitmq container
## check of docker container and microservices booting status while they are booting in background
## only the pre-condition application is done then trigger the next one to start
run-docker.sh

## check the processor if running before kill it
## kill microservices processors
## stop rabbitmq container
run-docker-stop.sh
```
