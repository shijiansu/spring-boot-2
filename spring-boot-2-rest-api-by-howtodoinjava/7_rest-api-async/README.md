Spring comes with `@EnableAsync` annotation and can be applied on application classes for asynchronous behavior. This annotation will look for methods marked with `@Async` annotation and run in background thread pools. The `@Async` annotated methods can return CompletableFuture to hold the result of an asynchronous computation.

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
