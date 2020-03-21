package spring.boot._2.by.zhuyu1991.rabbitmq;

import static spring.boot._2.by.zhuyu1991.rabbitmq.RabbitConfiguration.QUEUE_ONE_FOR_THREE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 3 receivers
@Slf4j
@Component
@RabbitListener(queues = QUEUE_ONE_FOR_THREE)
public class OneForThreeReceiver2 {

  @RabbitHandler
  public void process(String context) {
    log.info("[Receiver 2]: {}", context);
  }
}
