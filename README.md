![](https://img.shields.io/badge/language-java-blue)
![](https://img.shields.io/badge/technology-spring--boot2,%20restful%20api,%20async,%20spring%20test,%20docker-blue)
![](https://img.shields.io/badge/development%20year-2020-orange)
![](https://img.shields.io/badge/contributor-shijian%20su-purple)
![](https://img.shields.io/badge/license-MIT-lightgrey)

![](https://img.shields.io/github/languages/top/shijiansu/spring-boot-2-advanced-by-zhuyu1991)
![](https://img.shields.io/github/languages/count/shijiansu/spring-boot-2-advanced-by-zhuyu1991)
![](https://img.shields.io/github/languages/code-size/shijiansu/spring-boot-2-advanced-by-zhuyu1991)
![](https://img.shields.io/github/repo-size/shijiansu/spring-boot-2-advanced-by-zhuyu1991)
![](https://img.shields.io/github/last-commit/shijiansu/spring-boot-2-advanced-by-zhuyu1991?color=red)
![](https://github.com/shijiansu/spring-boot-2-advanced-by-zhuyu1991/workflows/ci%20build/badge.svg)

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
