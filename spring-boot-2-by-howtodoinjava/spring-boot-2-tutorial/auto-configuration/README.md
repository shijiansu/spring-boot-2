
- https://howtodoinjava.com/spring-boot2/springbootapplication-auto-configuration/

# @SpringBootApplication Annotation

This annotation is a shortcut of applying 3 annotations in one statement,

## @SpringBootConfiguration
`@SpringBootConfiguration` is **new annotation** in Spring boot 2. Previously, we have been using `@Configuration` annotation. You can use @Configuration in place of this. Both are same thing.

It indicates that a class provides Spring Boot application `@Configuration`. It simply means that annotated class is a configuration class and shall be scanned for further configurations and bean definitions.

## @EnableAutoConfiguration
This annotation is used to enable auto-configuration of the Spring Application Context, attempting to guess and configure beans that you are likely to need. Auto-configuration classes are usually applied based on your classpath and what beans you have defined.

Auto-configuration tries to be as intelligent as possible and will back-away as you define more of your own configuration. You can always manually exclude any configuration that you never want to apply using two methods –

i) Use `excludeName()`
ii) Using the `spring.autoconfigure.exclude` property in properties file. e.g.

```
@EnableAutoConfiguration(excludeName = {"multipartResolver","mbeanServer"})
```

Auto-configuration is always applied after user-defined beans have been registered.

## @ComponentScan
This annotation provides support parallel with Spring XML’s `context:component-scan` element.

Either `basePackageClasses()` or `basePackages()` may be specified to define specific packages to scan. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.

## 打印auto configuration的beans

pom.xml
```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
  </dependencies>
```

```shell
2019-06-30 22:05:35.445  INFO 8517 --- [           main] s.boot._2.based.on.howtodoinjava.App     : Starting App on sjh with PID 8517 (/Users/shijiansu/30it/spring-boot-2-based-on-howtodoinjava/auto-configuration/target/classes started by shijiansu in /Users/shijiansu/30it/spring-boot-2-based-on-howtodoinjava)
2019-06-30 22:05:35.453  INFO 8517 --- [           main] s.boot._2.based.on.howtodoinjava.App     : No active profile set, falling back to default profiles: default
2019-06-30 22:05:37.116  INFO 8517 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2019-06-30 22:05:37.156  INFO 8517 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-06-30 22:05:37.157  INFO 8517 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.21]
2019-06-30 22:05:37.261  INFO 8517 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-06-30 22:05:37.262  INFO 8517 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1703 ms
2019-06-30 22:05:37.492  INFO 8517 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-06-30 22:05:37.756  INFO 8517 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-06-30 22:05:37.761  INFO 8517 --- [           main] s.boot._2.based.on.howtodoinjava.App     : Started App in 2.96 seconds (JVM running for 3.723)
app # spring.boot._2.based.on.howtodoinjava.App$$EnhancerBySpringCGLIB$$33482fa7
applicationTaskExecutor # org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
basicErrorController # org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
beanNameHandlerMapping # org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
beanNameViewResolver # org.springframework.web.servlet.view.BeanNameViewResolver
characterEncodingFilter # org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter
conventionErrorViewResolver # org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver
defaultServletHandlerMapping # org.springframework.beans.factory.support.NullBean
defaultValidator # org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
defaultViewResolver # org.springframework.web.servlet.view.InternalResourceViewResolver
dispatcherServlet # org.springframework.web.servlet.DispatcherServlet
dispatcherServletRegistration # org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean
error # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$StaticView
errorAttributes # org.springframework.boot.web.servlet.error.DefaultErrorAttributes
errorPageCustomizer # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$ErrorPageCustomizer
errorPageRegistrarBeanPostProcessor # org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor
faviconHandlerMapping # org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
faviconRequestHandler # org.springframework.web.servlet.resource.ResourceHttpRequestHandler
formContentFilter # org.springframework.boot.web.servlet.filter.OrderedFormContentFilter
handlerExceptionResolver # org.springframework.web.servlet.handler.HandlerExceptionResolverComposite
hiddenHttpMethodFilter # org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter
httpRequestHandlerAdapter # org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
jacksonCodecCustomizer # org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$JacksonCodecConfiguration$$Lambda$340/1829966070
jacksonObjectMapper # com.fasterxml.jackson.databind.ObjectMapper
jacksonObjectMapperBuilder # org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
jsonComponentModule # org.springframework.boot.jackson.JsonComponentModule
localeCharsetMappingsCustomizer # org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration$LocaleCharsetMappingsCustomizer
loggingCodecCustomizer # org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$LoggingCodecConfiguration$$Lambda$339/1857910993
mappingJackson2HttpMessageConverter # org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
mbeanExporter # org.springframework.jmx.export.annotation.AnnotationMBeanExporter
mbeanServer # com.sun.jmx.mbeanserver.JmxMBeanServer
messageConverters # org.springframework.boot.autoconfigure.http.HttpMessageConverters
methodValidationPostProcessor # org.springframework.validation.beanvalidation.MethodValidationPostProcessor
multipartConfigElement # javax.servlet.MultipartConfigElement
multipartResolver # org.springframework.web.multipart.support.StandardServletMultipartResolver
mvcContentNegotiationManager # org.springframework.web.accept.ContentNegotiationManager
mvcConversionService # org.springframework.boot.autoconfigure.web.format.WebConversionService
mvcHandlerMappingIntrospector # org.springframework.web.servlet.handler.HandlerMappingIntrospector
mvcPathMatcher # org.springframework.util.AntPathMatcher
mvcResourceUrlProvider # org.springframework.web.servlet.resource.ResourceUrlProvider
mvcUriComponentsContributor # org.springframework.web.method.support.CompositeUriComponentsContributor
mvcUrlPathHelper # org.springframework.web.util.UrlPathHelper
mvcValidator # org.springframework.boot.autoconfigure.validation.ValidatorAdapter
mvcViewResolver # org.springframework.web.servlet.view.ViewResolverComposite
objectNamingStrategy # org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy
org.springframework.boot.autoconfigure.AutoConfigurationPackages # org.springframework.boot.autoconfigure.AutoConfigurationPackages$BasePackages
org.springframework.boot.autoconfigure.condition.BeanTypeRegistry # org.springframework.boot.autoconfigure.condition.BeanTypeRegistry
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration # org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration$$EnhancerBySpringCGLIB$$d001acf4
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration # org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration$$EnhancerBySpringCGLIB$$e12e93df
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration # org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$$EnhancerBySpringCGLIB$$faabed3a
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration # org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration$$EnhancerBySpringCGLIB$$2db9b3da
org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration # org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$$EnhancerBySpringCGLIB$$5e02e368
org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration # org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration$$EnhancerBySpringCGLIB$$678fafda
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration # org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$$EnhancerBySpringCGLIB$$531fb4a3
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$JacksonCodecConfiguration # org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$JacksonCodecConfiguration$$EnhancerBySpringCGLIB$$48523cf2
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$LoggingCodecConfiguration # org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$LoggingCodecConfiguration$$EnhancerBySpringCGLIB$$7d4e0d7e
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration # org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration$$EnhancerBySpringCGLIB$$1c722ebf
org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory # org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$$EnhancerBySpringCGLIB$$1e173ec6
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$$EnhancerBySpringCGLIB$$a96f493f
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration$$EnhancerBySpringCGLIB$$ee586350
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration$$EnhancerBySpringCGLIB$$3c0ba137
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$ParameterNamesModuleConfiguration # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$ParameterNamesModuleConfiguration$$EnhancerBySpringCGLIB$$d03ac06d
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration # org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration$$EnhancerBySpringCGLIB$$5fe390c2
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration # org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration$$EnhancerBySpringCGLIB$$6c17ba7c
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration # org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration$$EnhancerBySpringCGLIB$$94379dbc
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration # org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration$$EnhancerBySpringCGLIB$$5177b230
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration # org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration$$EnhancerBySpringCGLIB$$7b8e1b27
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration # org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$$EnhancerBySpringCGLIB$$f5e02188
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration # org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration$$EnhancerBySpringCGLIB$$e6a3ae74
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$$EnhancerBySpringCGLIB$$ceda7663
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletConfiguration # org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletConfiguration$$EnhancerBySpringCGLIB$$3077b16f
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration # org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration$$EnhancerBySpringCGLIB$$927f4616
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration$$EnhancerBySpringCGLIB$$b3ccd848
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration$$EnhancerBySpringCGLIB$$22bd6055
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration$$EnhancerBySpringCGLIB$$d0314909
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration$EmbeddedTomcat # org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration$EmbeddedTomcat$$EnhancerBySpringCGLIB$$4afa9390
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$$EnhancerBySpringCGLIB$$14ba601d
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration # org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration$$EnhancerBySpringCGLIB$$4b657226
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter # org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$$EnhancerBySpringCGLIB$$ed693d0b
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$FaviconConfiguration # org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$FaviconConfiguration$$EnhancerBySpringCGLIB$$a9a64551
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$$EnhancerBySpringCGLIB$$c24272b
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration$$EnhancerBySpringCGLIB$$20b8fbcb
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration$$EnhancerBySpringCGLIB$$eb580da3
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration # org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$$EnhancerBySpringCGLIB$$a1670b78
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration # org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration$$EnhancerBySpringCGLIB$$59123695
org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata # org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor # org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor # org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor # org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
org.springframework.context.annotation.internalConfigurationAnnotationProcessor # org.springframework.context.annotation.ConfigurationClassPostProcessor
org.springframework.context.event.internalEventListenerFactory # org.springframework.context.event.DefaultEventListenerFactory
org.springframework.context.event.internalEventListenerProcessor # org.springframework.context.event.EventListenerMethodProcessor
parameterNamesModule # com.fasterxml.jackson.module.paramnames.ParameterNamesModule
preserveErrorControllerTargetClassPostProcessor # org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$PreserveErrorControllerTargetClassPostProcessor
propertySourcesPlaceholderConfigurer # org.springframework.context.support.PropertySourcesPlaceholderConfigurer
requestContextFilter # org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter
requestMappingHandlerAdapter # org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
requestMappingHandlerMapping # org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
resourceHandlerMapping # org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
restTemplateBuilder # org.springframework.boot.web.client.RestTemplateBuilder
server-org.springframework.boot.autoconfigure.web.ServerProperties # org.springframework.boot.autoconfigure.web.ServerProperties
servletWebServerFactoryCustomizer # org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer
simpleControllerHandlerAdapter # org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
spring.http-org.springframework.boot.autoconfigure.http.HttpProperties # org.springframework.boot.autoconfigure.http.HttpProperties
spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties # org.springframework.boot.autoconfigure.info.ProjectInfoProperties
spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties # org.springframework.boot.autoconfigure.jackson.JacksonProperties
spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties # org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties # org.springframework.boot.autoconfigure.web.ResourceProperties
spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties # org.springframework.boot.autoconfigure.web.servlet.MultipartProperties
spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties # org.springframework.boot.autoconfigure.task.TaskExecutionProperties
spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties # org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
standardJacksonObjectMapperBuilderCustomizer # org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$StandardJackson2ObjectMapperBuilderCustomizer
stringHttpMessageConverter # org.springframework.http.converter.StringHttpMessageConverter
taskExecutorBuilder # org.springframework.boot.task.TaskExecutorBuilder
taskSchedulerBuilder # org.springframework.boot.task.TaskSchedulerBuilder
tomcatServletWebServerFactory # org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
tomcatServletWebServerFactoryCustomizer # org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer
tomcatWebServerFactoryCustomizer # org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer
viewControllerHandlerMapping # org.springframework.beans.factory.support.NullBean
viewResolver # org.springframework.web.servlet.view.ContentNegotiatingViewResolver
webServerFactoryCustomizerBeanPostProcessor # org.springframework.boot.web.server.WebServerFactoryCustomizerBeanPostProcessor
websocketServletWebServerCustomizer # org.springframework.boot.autoconfigure.websocket.servlet.TomcatWebSocketServletWebServerCustomizer
welcomePageHandlerMapping # org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping
2019-06-30 22:05:37.785  INFO 8517 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
```
