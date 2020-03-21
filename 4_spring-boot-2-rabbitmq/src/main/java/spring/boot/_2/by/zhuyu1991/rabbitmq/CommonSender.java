package spring.boot._2.by.zhuyu1991.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonSender {

  @Autowired AmqpTemplate rabbitTemplate;

  public void send(String queueName, String message) {
    rabbitTemplate.convertAndSend(queueName, message);
    log.info("[Sender 1]: {}", message);
  }
}
