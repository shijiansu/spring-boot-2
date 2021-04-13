# Update for original examples

- provide new example of StringRedisTemplate
- provide new example of without the distributed lock
- redisson
  - https://github.com/redisson/redisson
  - https://blog.csdn.net/zhuyu19911016520/article/details/80418161
- introduce of embedded redis - https://github.com/ozimov/embedded-redis
- introduce of redis docker - refer to stack.yml

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

## 为什么要使用分布式锁

在分布式场景下为了保证数据最终一致性。在单进程的系统中，存在多个线程可以同时改变某个变量（可变共享变量）时，就需要对变量或代码块做同步(lock—synchronized)，使其在修改这种变量时能够线性执行消除并发修改变量。但分布式系统是多部署、多进程的，开发语言提供的并发处理API在此场景下就无能为力了。

## 分布式锁的使用场景

电商网站用下单操作时需要使用，秒杀活动更是如此，否则会出现超卖（库存100，秒杀活动时库存变负数了）现象

## 分布式锁的实现方式

- 大概有三种：1.基于关系型数据库，2.基于缓存，3基于zookeeper
- 大部分网站使用的是基于缓存的，有更好的性能，而缓存一般是以集群方式部署，保证了高可用性
