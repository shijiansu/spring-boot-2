package cn.timebusker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

// wrapper from properties
@ConfigurationProperties("calculate")
@Getter
@Setter
public class CalculateProperties {

  private int scale;

}
