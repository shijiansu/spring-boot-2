package spring.boot._2.by.zhuyu1991.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
  public static final String QUEUE_HELLO_WORLD = "helloworld";
  public static final String QUEUE_ONE_FOR_THREE = "oneforthree";

  // The method name is the bean name.

  @Bean
  public Queue queueHelloWorld() {
    return new Queue(QUEUE_HELLO_WORLD, false);
  }

  @Bean
  public Queue queueOneForThree() {
    return new Queue(QUEUE_ONE_FOR_THREE, false);
  }
}
