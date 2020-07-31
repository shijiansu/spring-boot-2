Spring boot, by default, includes Jackson 2 dependency and is part of spring-boot-starter-json. Using `JacksonAutoConfiguration class`, spring boot automatically configures Jackson with following behavior:

- an ObjectMapper in case none is already configured.
- a Jackson2ObjectMapperBuilder in case none is already configured.
- auto-registration for all Module beans with all ObjectMapper beans (including the defaulted ones).

# Script step by step

```shell script
./mvnw clean spring-boot:run
```

# One stop script
```shell script
/bin/bash run.sh
/bin/bash run-test.sh
/bin/bash run-stop.sh
```
