package spring.boot._2.by.zhuyu1991.rabbitmq;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({QpidProperties.class, EmbeddedAmqpBroker.class})
public class _2_RabbitMQTest extends _1_RabbitMQManulTest {}
