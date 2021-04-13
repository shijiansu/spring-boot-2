![](https://img.shields.io/badge/language-java-blue)
![](https://img.shields.io/badge/technology-spring%20boot-blue)
![](https://img.shields.io/badge/development%20year-2020-orange)
![](https://img.shields.io/badge/contributor-shijian%20su-purple)
![](https://img.shields.io/badge/license-MIT-lightgrey)

![](https://img.shields.io/github/languages/top/shijiansu/spring-boot)
![](https://img.shields.io/github/languages/count/shijiansu/spring-boot)
![](https://img.shields.io/github/languages/code-size/shijiansu/spring-boot)
![](https://img.shields.io/github/repo-size/shijiansu/spring-boot)
![](https://img.shields.io/github/last-commit/shijiansu/spring-boot?color=red)
![](https://github.com/shijiansu/spring-boot/workflows/ci%20build/badge.svg)

--------------------------------------------------------------------------------

# Table of Contents

## 2017-spring-5.0-microservices-2nd

- ch3_1_hello-world-service-groovy - example of Groovy
- ch3_2_greeting-restful-service - RESTFul API, HATEOAS, Swagger
- ch3_2_greeting-webflux-service
- ch3_2_greeting-messaging-service
- ch3_2_greeting-security-service - example of the simplest basic authentication as default
- ch3_2 - Few TODO from the book examples
  - Need to update the security/oauth2 solutions
  - Need to update the actuator solutions
  - Spring Data REST HAL Browser is deprecated! Prefer the HAL Explorer (artifactId: spring-data-rest-hal-explorer)!
  - Swagger UI is not working
- ch3_3_springboot_2_0 - example of SpringBoot2, RESTFul APIs, RabbitMQ, email sending, FakeSMTP, Docker
- ch6_1_springboot_2_3 - example of SpringBoot2, RESTFul APIs, WebFlux, RabbitMQ, Microservices, Docker, Dockerfile, Docker wait for dependencies, Docker Compose, Docker Network, Nginx (reverse proxy)
  - book - SpringBoot2
  - checkin - SpringBoot2
  - fares - SpringBoot2
  - search - SpringBoot2
  - website - SpringBoot2 + Thymeleaf
  - microservice-gateway - Nginx

## spring-boot-2-advanced-by-zhuyu1991

- 1_spring-boot-2-hikaricp - example of Hikaricp, embedded database (H2), JPA
- 2_spring-boot-2-mongodb - example of MongoDB, MongoTemplate
- 3_spring-boot-2-redis-redisson - example of Redisson, embedded Redis, Redis Docker, StringRedisTemplate
- 4_spring-boot-2-rabbitmq - example of RabbitMQ, embedded AMQP (apid, broker), RabbitMQ Docker, AmqpTemplate, @RabbitListener
- 5_spring-boot-2-caffeine - example of Caffeine cache, cache manger, @EnableCaching

## spring-boot-2-by-timebusker

- spring-boot-core-1-schedule - examples of
- spring-boot-core-2-async - examples of
- spring-boot-core-3-aop - examples of
- spring-boot-core-4-defining-starter - examples of
- spring-boot-data-1-spring-data - examples of
- spring-boot-data-2-ehcache - examples of
- spring-boot-data-3-jdbc-template - examples of
- spring-boot-data-4-mybatis - examples of
- spring-boot-data-4-transcation - examples of
- spring-boot-data-5-redis - examples of
- spring-boot-data-6-mq - examples of
- spring-boot-data-7-mongodb - examples of
- spring-boot-data-9-multi-datasource - examples of
- spring-boot-skeleton-project-example - examples of
- spring-boot-web-1-quickstart - examples of
- spring-boot-web-2-restful - examples of
- spring-boot-web-3-freemarker - examples of
- spring-boot-web-4-swagger2 - examples of
- spring-boot-web-5-monitor-actuator - examples of
- spring-boot-web-6-spring-security - examples of
## spring-boot-2-dockerizing

- 1_spring-boot-2-docker
  - 1, 2, 3 are similar examples, but with different tools and plugins
  - example of SpringBoot2, Maven, Docker, com.google.cloud.tools:jib-maven-plugin (via command line)
- 2_spring-boot-2-docker-gradle
  - 1, 2, 3 are similar examples, but with different tools and plugins
  - example of SpringBoot2, Gradle, Docker, com.google.cloud.tools:jib-gradle-plugin (in build.gradle)
- 3_spring-boot-2-docker-spotify
  - 1, 2, 3 are similar examples, but with different tools and plugins
  - example of SpringBoot2, Maven, Docker, com.spotify:dockerfile-maven-plugin
- 4_spring-boot-2-docker-jar-java13 - example of SpringBoot2, Java13, Docker, Dockerfile, Docker Network, Docker Compose
  - springboot2-docker - very simple microservice demo with SpringBoot2
  - springboot2-docker-client - very simple microservice demo with SpringBoot2, call API to springboot2-docker

## spring-boot-2-reactive-by-liukang

- 1_reactive-web-and-data
  - _1_reactive - example of Reactive, Reactive Stream, lambda, Reactor3
  - _2_webflux
    - _1_reactive_web - example of WebFlux + web test client
    - _2_reactive_web_router - example of router + handler
    - _3_reactive_data - example of reactive data (MongoDB) + embedded MongoDB (flapdoodle), Spring profile - be careful date data format
    - _4_sse_event - example of server send event + MongoDB driver @Tailable. Bash inserting data with sleep and curl is listening as request client.
- 2_reactive-streams - example of define classes compiling to reactive-streams <https://github.com/reactive-streams/reactive-streams-jvm> 
- 3_reactor3
  - _1_reactor_api - example of basic reactor api
  - _2_backpressure - example of different backpressure strategies
  - _3_scheduler - example of different schedulers
  - _4_operator - example of transform() and transformDeferred() (previous compose()) 
  - _5_test_and_debug - example of testing util, e.g. Stepverifier, Publihserprobe; and debug util, e.g. Hook, checkpoint.
  - _6_cold_vs_hot - example of cold and hot sequences
- 4_docker_event_in_action - example of docker event client (docker-java) + docker event (docker run)

## spring-boot-2-reactive-by-stackabuse

- 1_reactive-webflux - example of WebFlux, server send event
- 2_reactive-route - example of routing, RouterFunction, ServerRequest, ServerResponse
- 3_reactive-websocket - example of WebSocket (server side, client side)
- 4_reactive-webclient - example of WebClient, SpringBoot test, reactor test, Given-When-Then unit test
- 5_reactive-mongodb - example of reactive data (MongoDB), Spring data, MongoDB Docker

## spring-boot-2-rest-api-by-howtodoinjava

- 1_rest-api - example of the RESTFul API, i18n, Unit Test (Mockito) for DAO, Integration Test (SpringBootTest)
- 2_rest-api-jackson - example of the RESTFul API, custom Jackson parser configuration
- 3_rest-api-gson - example of the RESTFul API, GSON (pom.xml)
- 4_rest-api-headers-with-exceptionhandler - example of required parameter in the header, global exception handler
- 5_rest-api-validation-with-exceptionhandler - example of validator in request, global exception handler (more examples)
- 6_rest-api-callable - example of callable response in controller for async (beneficial to server)
- 7_rest-api-async - example of @Async, CompletableFuture in service / controller for async (beneficial server and application integration). Similar as WebFlux but using the multi-threading.
- 8_rest-api-responsebodyemitter - example of response body emitter in controller for async (beneficial browser). Reduce the browser waiting time.
- 9_rest-api-sseemitter - example of server sent event in controller for async (beneficial to browser). Reduce the browser waiting time. Response is as stream back to browser 1 by 1

## spring-boot-2-test-by-howtodoinjava

- 1_test-springboot - example of creating the test client, mock response for RestTemplate
  - @SpringBootTest
  - Not Slicing, fully initializes the application context
  - @Mock & @InjectMocks, @TestConfiguration, TestRestTemplate, embedded database (H2), @Sql
- 2_test-mvc-webmvc - example of creating the test client
  - @WebMvcTest
  - Disables full auto-configuration, focus on @Controller
  - MockMvc (web client), ObjectMapper, MockMvcRequestBuilders, MockMvcResultHandlers
- 3_test-mvc-restclient - example of creating the mock response for RestTemplate
  - @RestClientTest
  - Disable full auto-configuration, focus on Service, RestTemplate
  - @AutoConfigureWebClient, MockRestServiceServer (Mock server)
- 4_test-data-jpa - example of creating mock database for Spring Data JPA
  - @DataJpaTest
  - Disable full auto-configuration, focus on @Repository
  - the embedded database (h2), TestEntityManager
- 5_test-reactive-webflux - example of slicing for the WebFlux controller, importing the service, mock the repository, creating test client
  - @WebFluxTest
  - Disable full auto-configuration, focus on @Controller
  - @Import (for service), WebTestClient (WebFlux web client), @MockBean (similar @Bean)

--------------------------------------------------------------------------------

# 查看SprintBoot

`curl https://start.spring.io > springboot.md`

# Execute all tests in repo

`/bin/bash run-repo-test.sh`
