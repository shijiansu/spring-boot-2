package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
// @EnableReactiveMongoRepositories(basePackageClasses = {Employee.class}) // as same package
public class MongoClientConfiguration extends AbstractReactiveMongoConfiguration {
  @Value("${mongodb.port}")
  private String port;

  @Value("${mongodb.databasename}")
  private String databaseName;

  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }

  @Override
  protected String getDatabaseName() {
    return databaseName;
  }

  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate() {
    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }
}
