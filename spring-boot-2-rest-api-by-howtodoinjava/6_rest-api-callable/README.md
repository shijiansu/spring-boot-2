Using mockito shipped with spring boot distribution.

> https://adrianobrito.github.io/2018/01/11/using-callable-responses-in-spring-mvc/

Spring MVC handles controller methods that return Callable as asynchronous requests, so the code inside the lambda is executed in a servlet’s AsyncContext behind the scenes. It’s recommendable to **put only the heavy work inside the Callable** anonymous function, in case of having extra basic logic into the controller method code.

- Related topic - `DeferredResult`

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

Callable API is with another set of threads

```text
2020-03-03 21:49:37.071  INFO 43928 --- [io-8080-exec-10] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.089  INFO 43928 --- [io-8080-exec-12] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.089  INFO 43928 --- [io-8080-exec-13] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.089  INFO 43928 --- [io-8080-exec-11] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.093  INFO 43928 --- [io-8080-exec-15] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.095  INFO 43928 --- [io-8080-exec-16] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.095  INFO 43928 --- [io-8080-exec-17] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.102  INFO 43928 --- [     mvc-task-1] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.103  INFO 43928 --- [     mvc-task-3] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.103  INFO 43928 --- [     mvc-task-2] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.104  INFO 43928 --- [     mvc-task-4] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.105  INFO 43928 --- [     mvc-task-5] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.106  INFO 43928 --- [     mvc-task-8] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.107  INFO 43928 --- [    mvc-task-10] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.108  INFO 43928 --- [     mvc-task-6] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.109  INFO 43928 --- [     mvc-task-7] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.109  INFO 43928 --- [     mvc-task-9] s.b._2.by.howtodoinjava.HelloController  : hello
2020-03-03 21:49:37.120  INFO 43928 --- [nio-8080-exec-8] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.120  INFO 43928 --- [nio-8080-exec-9] s.b._2.by.howtodoinjava.HelloController  : goodbye
2020-03-03 21:49:37.139  INFO 43928 --- [nio-8080-exec-2] s.b._2.by.howtodoinjava.HelloController  : goodbye
```