`ResponseBodyEmitter` helps to collect and send the response to the client. It is a controller method return value type for asynchronous request processing where one or more objects are written to the response.

While `DeferredResult` is used to produce a single result, a `ResponseBodyEmitter` can be used to send multiple objects where each object is written with a compatible `HttpMessageConverter`.

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
