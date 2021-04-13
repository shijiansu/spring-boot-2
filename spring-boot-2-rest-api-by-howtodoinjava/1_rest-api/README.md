# How to kill SpringBoot application here

## Version 2

- Application.java -> Using ApplicationPidFileWriter to write pid file
- Kill pid in run.sh -> kill $(cat ./application.pid) // keeps default location, when SpringBoot stop, application.pid will be gone

## Version 1

- Application.java -> Using ApplicationPidFileWriter to write pid file
- At application.properties -> spring.pid.file=target/application.pid
- Kill pid in run.sh -> kill $(cat ./target/application.pid)

# Java development environment

`sdk list java && sdk current java`

# Setup Maven wrapper

- https://github.com/takari/maven-wrapper

`mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.5.4`

# Script step by step

```bash
./mvnw clean spring-boot:run
```

# One stop script
```bash
/bin/bash run.sh
/bin/bash run-test.sh
/bin/bash run-stop.sh
```
