Publisher是能够发出元素的发布者

```java
public interface Publisher<T> {
    public void subscribe(Subscriber<? super T> s);
}
```

Subscriber是接收元素并做出响应的订阅者

```java
public interface Subscriber<T> {
    public void onSubscribe(Subscription s);
    public void onNext(T t);
    public void onError(Throwable t);
    public void onComplete();
}
```

Subscription是Publisher和Subscriber的“中间人”

```java
public interface Subscription {
    public void request(long n);
    public void cancel();
}
```

Processor集Publisher和Subscriber于一身

```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {}
```

这四个接口在`JEP 266`跟随`Java 9`版本被引入了`Java SDK` - `java.util.concurrent.Flow`

# Java development environment

`sdk list java && sdk current java`

# Setup Maven wrapper

- https://github.com/takari/maven-wrapper

`mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.5.4`
