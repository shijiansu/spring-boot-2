
- <https://howtodoinjava.com/spring-boot2/testing/testing-support> / 2020

# Call flow for testing

client (acting as WebApp/App/backend client) -> controller -> service / repository -> integration (as downstream APIs/service)

# Types of test in examples

1. Unit Test - mock the dependencies and only face the interface of unit/component
2. Manual Test - not active by maven test, mostly has dependencies (e.g. database, downstream APIs) with manual test exection steps 
3. Integration Test - boot up whole application with additional testing configuration (overwriting production settings to testing env setting)
4. Slicing Test - inject related objects in the layers, e.g. web/service/data/integration (web client). Reducing the testing time by not load all (Spring) framework objects

# spring.jpa.hibernate.ddl-auto

- <https://docs.spring.io/autorepo/docs/spring-boot/2.2.5.RELEASE/reference/htmlsingle/#boot-features-creating-and-dropping-jpa-databases>

JPA databases are automatically `created` only if you use an embedded database (`H2`, `HSQL`, or `Derby`)
