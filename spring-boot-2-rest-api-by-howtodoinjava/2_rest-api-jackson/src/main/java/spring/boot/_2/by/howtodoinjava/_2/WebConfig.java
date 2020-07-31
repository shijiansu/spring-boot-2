package spring.boot._2.by.howtodoinjava._2;

import com.fasterxml.jackson.annotation.JsonInclude;
// import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
// import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class WebConfig {
  // 由下列方式可见, SpringBoot对Jackson相关的实例化的一个流程
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customJsonOption1() {
    return builder -> {
      builder
          .indentOutput(true) // human readable
          .serializationInclusion(JsonInclude.Include.NON_NULL) // not null
          .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // Use _ not CamelCase
    };
  }

  //  @Bean
  //  @Primary
  //  public ObjectMapper customJsonOption2() {
  //    return new Jackson2ObjectMapperBuilder()
  //        .indentOutput(true)
  //        .serializationInclusion(JsonInclude.Include.NON_NULL)
  //        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
  //        .build();
  //  }

  //  @Bean
  //  public Jackson2ObjectMapperBuilder customJsonOption3() {
  //    return new Jackson2ObjectMapperBuilder()
  //        .indentOutput(true)
  //        .serializationInclusion(JsonInclude.Include.NON_NULL)
  //        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  //  }

//  @Bean
//  public MappingJackson2HttpMessageConverter customJsonOption4() {
//    return new MappingJackson2HttpMessageConverter(
//        new Jackson2ObjectMapperBuilder()
//            .indentOutput(true)
//            .serializationInclusion(JsonInclude.Include.NON_NULL)
//            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
//            .build());
//    }
}
