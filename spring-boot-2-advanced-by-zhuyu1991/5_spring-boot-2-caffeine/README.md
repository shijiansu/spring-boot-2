# Update for original examples

- need to be careful of cache manager and how to load the caches, and double validate that if the cache exists or not
- provide new example of beforeInvocat
- provide new example of @CachePut
- provide new example of @Caching
- provide new example of @Cacheable in annotation

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
