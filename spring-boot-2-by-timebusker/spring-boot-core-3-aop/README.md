
## Reference
- <https://www.baeldung.com/spring-aop-pointcut-tutorial>
- <http://blog.csdn.net/moreevan/article/details/11977115/>
- <http://jinnianshilongnian.iteye.com/blog/1415606>

`this` limits matching to join points where the bean reference is an `instance of the given type`, while `target` limits matching to join points where the `target object is an instance of the given type`. The former works when Spring AOP creates a CGLIB-based proxy, and the latter is used when a JDK-based proxy is created. Suppose that the target class implements an interface:

```java
public class FooDao implements BarDao {
}
```

In this case, Spring AOP will use the `JDK-based` proxy and you should use the target PCD because the proxied object will be an instance of `Proxy class` and implement the BarDao interface:

```java
@Pointcut("target(org.baeldung.dao.BarDao)")
```

On the other hand if FooDao doesn’t implement any interface or proxyTargetClass property is set to true then the proxied object will be a subclass of FooDao and the this PCD could be used:

````java
@Pointcut("this(org.baeldung.dao.FooDao)")
````
