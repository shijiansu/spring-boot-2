package reactive.by.liukang._2_webflux._4_sse_event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

@Slf4j
@Configuration
public class EventConfiguration {

  public EventConfiguration() {
    log.info("EventConfiguration");
  }

  @Bean
  public CommandLineRunner init(MongoOperations mongo) {
    // control max size of Collection in
    return (String... args) -> {
      mongo.dropCollection(Event.class);
      mongo.createCollection(
          Event.class,
          CollectionOptions.empty()
              // .size(200)// 以byte为单位的大小，并且会向上取到256的整倍数
              .maxDocuments(200) // max 200 records
              // max siez, must define, because MongoDB is no size definition for
              // collumns and fields
              .size(100_000)
              .capped());
    };
  }
}
