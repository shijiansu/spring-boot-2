package cn.timebusker.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取额外的配置文件的配置信息
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
@Data
public class DubboConfig {

  @Value("${dubbo.resAddress}")
  private String resAddress;

  @Value("${dubbo.appName}")
  private String appName;

  @Value("${dubbo.resUsername}")
  private String resUsername;

  @Value("${dubbo.resPassowrd}")
  private String resPassowrd;

  @Value("${dubbo.protocol}")
  private String protocol;

  @Value("${dubbo.port}")
  private int port;

  @Value("${dubbo.accepts}")
  private int accepts;

  @Value("${dubbo.connections}")
  private int connections;
}
