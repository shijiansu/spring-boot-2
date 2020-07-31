![](https://img.shields.io/badge/language-java-blue)
![](https://img.shields.io/badge/technology-spring--boot2,%20restful%20api,%20async,%20spring%20test,%20docker,%20spring%20reactive,%20reactor3,%20webflux,%20mongodb,%20embedded%20mongodb-blue)
![](https://img.shields.io/badge/development%20year-2020-orange)
![](https://img.shields.io/badge/contributor-shijian%20su-purple)
![](https://img.shields.io/badge/license-MIT-lightgrey)

![](https://img.shields.io/github/languages/top/shijiansu/spring-boot-2)
![](https://img.shields.io/github/languages/count/shijiansu/spring-boot-2)
![](https://img.shields.io/github/languages/code-size/shijiansu/spring-boot-2)
![](https://img.shields.io/github/repo-size/shijiansu/spring-boot-2)
![](https://img.shields.io/github/last-commit/shijiansu/spring-boot-2?color=red)
![](https://github.com/shijiansu/spring-boot-2/workflows/ci%20build/badge.svg)

--------------------------------------------------------------------------------

- spring-boot-2-advanced-by-zhuyu1991
  - 1_spring-boot-2-hikaricp - examples of Hikaricp, embedded database (H2), JPA
  - 2_spring-boot-2-mongodb - examples of MongoDB, MongoTemplate
  - 3_spring-boot-2-redis-redisson - examples of Redisson, embedded Redis, Redis Docker, StringRedisTemplate
  - 4_spring-boot-2-rabbitmq - examples of RabbitMQ, embbeded AMQP (apid, broker), RabbitMQ Docker, AmqpTemplate, @RabbitListener
  - 5_spring-boot-2-caffeine - examples of Caffeine cache, cache manger, @EnableCaching
- spring-boot-2-dockerizing
  - 1_spring-boot-2-docker - examples of springboot2, maven, docker, com.google.cloud.tools:jib-maven-plugin
  - 2_spring-boot-2-docker-gradle - examples of springboot2, gradle, docker, com.google.cloud.tools:jib-maven-plugin
  - 3_spring-boot-2-docker-spotify - examples of springboot2, maven, docker, com.spotify:dockerfile-maven-plugin
- spring-boot-2-reactive-by-liukang
  - 1_reactive-web-and-data
    - _1_reactive - examples of reactive, reactive stream, lambda, reactor3
    - _2_webflux
      - _1_reactive_web - examples of webflux + web test client
      - _2_reactive_web_router - examples of router + handler
      - _3_reactive_data - examples of reactive data (mongodb) + embedded mongodb (flapdoodle), spring profile - be careful date data format
      - _4_sse_event - examples of server sned event + mongodb driver @Tailable. Bash inserting data with sleep and curl is lisenting as request client
  - 2_reactive-streams - examples of define classes compiling to reactive-streams <https://github.com/reactive-streams/reactive-streams-jvm> 
  - 3_reactor3
    - _1_reactor_api - examples of basic reactor api
    - _2_backpressure - examples of different backpressure strategies
    - _3_scheduler - examples of different schedulers
    - _4_operator - examples of transform() and transformDeferred() (previous compose()) 
    - _5_test_and_debug - examples of testing util, e.g. stepverifier, publihserprobe; and debug util, e.g. Hook, checkpoint.
    - _6_cold_vs_hot - examples of cold and hot sequences
  - 4_docker_event_in_action - examples of docker event client (docker-java) + docker event (docker run)
- spring-boot-2-reactive-by-stackabuse
  - 1_reactive-webflux - examples of webflux, server send event
  - 2_reactive-route - examples of routing, RouterFunction, ServerRequest, ServerResponse
  - 3_reactive-websocket - examples of web sockert (server side, client side)
  - 4_reactive-webclient - examples of webclient, springboot test, reactor test, Given-When-Then unit test
  - 5_reactive-mongodb - examples of reactive data (mongodb), spring data, mongodb docker
- spring-boot-2-rest-api-by-howtodoinjava
  - 1_rest-api - examples of the RESTful API, i18n, Unit Test (Mockito) for DAO, Integration Test (SpringBootTest)
  - 2_rest-api-jackson - examples of the RESTful API, custom Jackson parser configuration
  - 3_rest-api-gson - examples of the RESTful API, GSON (pom.xml)
  - 4_rest-api-headers-with-exceptionhandler - examples of required parameter in the header, global exception handler
  - 5_rest-api-validation-with-exceptionhandler - examples of validator in request, global exception handler (more examples)
  - 6_rest-api-callable - examples of callable response in controller for async (beneficial to server)
  - 7_rest-api-async - examples of @Async, CompletableFuture in service / controller for async (beneficial server and application integration). Similar as WebFlux but using the multi-threading
  - 8_rest-api-responsebodyemitter - examples of response body emitter in controller for async (beneficial browser). Reduce browser waiting time
- 9_rest-api-sseemitter - examples of server sent event in controller for async (beneficiate browser). Reduce the browser waiting time. Response is as stream back to browser 1 by 1
- spring-boot-2-test-by-howtodoinjava
  - 1_test-springboot - examples of creating the test client, mock response for RestTemplate
    - @SpringBootTest
    - Not Slicing, fully initializes the application context
    - @Mock & @InjectMocks, @TestConfiguration, TestRestTemplate, embedded database (H2), @Sql
  - 2_test-mvc-webmvc - examples of creating the test client
    - @WebMvcTest
    - Disables full auto-configuration, focus on @Controller
    - MockMvc (web client), ObjectMapper, MockMvcRequestBuilders, MockMvcResultHandlers
  - 3_test-mvc-restclient - examples of creating the mock response for RestTemplate
    - @RestClientTest
    - Disable full auto-configuration, focus on Service, RestTemplate
    - @AutoConfigureWebClient, MockRestServiceServer (Mock server)
  - 4_test-data-jpa - examples of creating mock database for Spring Data JPA
    - @DataJpaTest
    - Disable full auto-configuration, focus on @Repository
    - the embedded database (h2), TestEntityManager
  - 5_test-reactive-webflux - examples of slicing for the WebFlux controller, importing the service, mock the repository, creating test client
    - @WebFluxTest
    - Disable full auto-configuration, focus on @Controller
    - @Import (for service), WebTestClient (WebFlux web client), @MockBean (similar @Bean)

--------------------------------------------------------------------------------

# Execute all tests in repo

`/bin/bash run-repo-test.sh`
