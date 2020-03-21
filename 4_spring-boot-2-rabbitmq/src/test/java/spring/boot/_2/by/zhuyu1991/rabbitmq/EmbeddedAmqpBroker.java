package spring.boot._2.by.zhuyu1991.rabbitmq;

import javax.annotation.PostConstruct;
import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class EmbeddedAmqpBroker {
  Broker broker;
  BrokerOptions brokerOptions;

  public EmbeddedAmqpBroker(QpidProperties qpidProperties) {
    broker = new Broker();
    brokerOptions = new BrokerOptions();
    // option values set to "test-initial-config.json"
    brokerOptions.setConfigProperty(
        "qpid.broker.defaultPreferenceStoreAttributes", "{\"type\": \"Noop\"}");
    brokerOptions.setConfigProperty("qpid.amqp_port", qpidProperties.getQpidPort());
    brokerOptions.setConfigProperty("qpid.vhost", qpidProperties.getQpidVirtualHost());
    brokerOptions.setConfigProperty("qpid.username", qpidProperties.getQpidUserName());
    brokerOptions.setConfigProperty("qpid.password", qpidProperties.getQpidPassword());
    brokerOptions.setConfigurationStoreType("Memory");
    brokerOptions.setStartupLoggedToSystemOut(false);
  }

  @PostConstruct
  public void postConstruct() throws Exception {
    broker.startup(brokerOptions);
  }

  // No need below, while test is completed it auto attemps to shutdown broker
  //  @PreDestroy
  //  public void preDestroy() {
  //    broker.shutdown();
  //  }
}
