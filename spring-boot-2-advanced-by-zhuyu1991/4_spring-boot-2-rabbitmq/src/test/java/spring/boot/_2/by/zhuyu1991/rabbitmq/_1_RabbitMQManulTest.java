package spring.boot._2.by.zhuyu1991.rabbitmq;

import static spring.boot._2.by.zhuyu1991.rabbitmq.RabbitConfiguration.QUEUE_HELLO_WORLD;
import static spring.boot._2.by.zhuyu1991.rabbitmq.RabbitConfiguration.QUEUE_ONE_FOR_THREE;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class _1_RabbitMQManulTest {
  @Autowired CommonSender commonSender;

  @Test
  public void hello_world() {
    IntStream.range(0, 100)
        .forEach(
            i -> {
              commonSender.send(QUEUE_HELLO_WORLD, "Hello World 1: " + i);
              commonSender.send(QUEUE_HELLO_WORLD, "Hello World 2: " + i);
            });
  }

  @Test
  public void one_for_three() {
    IntStream.range(0, 100)
        .forEach(i -> commonSender.send(QUEUE_ONE_FOR_THREE, "1 Sender to 3 Receivers: " + i));
  }
}
