
- Reference: https://howtodoinjava.com/spring-boot2/springbootapplication-auto-configuration/

## @SpringBootApplication Annotation

This annotation is a shortcut of applying 3 annotations in one statement,

### @SpringBootConfiguration
`@SpringBootConfiguration` is **new annotation** in Spring boot 2. Previously, we have been using `@Configuration` annotation. You can use @Configuration in place of this. Both are same thing.

It indicates that a class provides Spring Boot application `@Configuration`. It simply means that annotated class is a configuration class and shall be scanned for further configurations and bean definitions.

### @EnableAutoConfiguration
This annotation is used to enable auto-configuration of the Spring Application Context, attempting to guess and configure beans that you are likely to need. Auto-configuration classes are usually applied based on your classpath and what beans you have defined.

Auto-configuration tries to be as intelligent as possible and will back-away as you define more of your own configuration. You can always manually exclude any configuration that you never want to apply using two methods –

i) Use `excludeName()`
ii) Using the `spring.autoconfigure.exclude` property in properties file. e.g.

```
@EnableAutoConfiguration(excludeName = {"multipartResolver","mbeanServer"})
```

Auto-configuration is always applied after user-defined beans have been registered.

### @ComponentScan
This annotation provides support parallel with Spring XML’s `context:component-scan` element.

Either `basePackageClasses()` or `basePackages()` may be specified to define specific packages to scan. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.

### 打印auto configuration的beans

pom.xml
```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
  </dependencies>
```

```shell

2019-06-30 22:12:48.721  INFO 8873 --- [           main] s.boot._2.based.on.howtodoinjava.App     : Starting App on sjh with PID 8873 (/Users/shijiansu/30it/spring-boot-2-based-on-howtodoinjava/auto-configuration-main-only/target/classes started by shijiansu in /Users/shijiansu/30it/spring-boot-2-based-on-howtodoinjava)
2019-06-30 22:12:48.724  INFO 8873 --- [           main] s.boot._2.based.on.howtodoinjava.App     : No active profile set, falling back to default profiles: default
2019-06-30 22:12:50.376  INFO 8873 --- [           main] s.boot._2.based.on.howtodoinjava.App     : Started App in 2.43 seconds (JVM running for 3.341)
app # spring.boot._2.based.on.howtodoinjava.App$$EnhancerBySpringCGLIB$$5679f0d7
2019-06-30 22:12:50.394  INFO 8873 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
applicationTaskExecutor # org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
mbeanExporter # org.springframework.jmx.export.annotation.AnnotationMBeanExporter
mbeanServer # com.sun.jmx.mbeanserver.JmxMBeanServer
objectNamingStrategy # org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy
org.springframework.boot.autoconfigure.AutoConfigurationPackages # org.springframework.boot.autoconfigure.AutoConfigurationPackages$BasePackages
org.springframework.boot.autoconfigure.condition.BeanTypeRegistry # org.springframework.boot.autoconfigure.condition.BeanTypeRegistry
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration # org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration$$EnhancerBySpringCGLIB$$f3336e24
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration # org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration$$EnhancerBySpringCGLIB$$460550f
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration # org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration$$EnhancerBySpringCGLIB$$3fa3efef
org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory # org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration # org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration$$EnhancerBySpringCGLIB$$831551f2
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration # org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration$$EnhancerBySpringCGLIB$$8f497bac
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration # org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration$$EnhancerBySpringCGLIB$$b7695eec
org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata # org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor # org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor # org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor # org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
org.springframework.context.annotation.internalConfigurationAnnotationProcessor # org.springframework.context.annotation.ConfigurationClassPostProcessor
org.springframework.context.event.internalEventListenerFactory # org.springframework.context.event.DefaultEventListenerFactory
org.springframework.context.event.internalEventListenerProcessor # org.springframework.context.event.EventListenerMethodProcessor
propertySourcesPlaceholderConfigurer # org.springframework.context.support.PropertySourcesPlaceholderConfigurer
spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties # org.springframework.boot.autoconfigure.info.ProjectInfoProperties
spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties # org.springframework.boot.autoconfigure.task.TaskExecutionProperties
spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties # org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
taskExecutorBuilder # org.springframework.boot.task.TaskExecutorBuilder
taskSchedulerBuilder # org.springframework.boot.task.TaskSchedulerBuilder
2019-06-30 22:12:50.398  INFO 8873 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
```