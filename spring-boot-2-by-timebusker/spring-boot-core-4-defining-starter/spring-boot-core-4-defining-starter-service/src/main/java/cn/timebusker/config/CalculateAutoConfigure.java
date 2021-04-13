package cn.timebusker.config;

import cn.timebusker.service.CalculateService;
import cn.timebusker.service.CalculateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CalculateServiceImpl.class) // when found the class
@EnableConfigurationProperties(CalculateProperties.class) // enable to auto load properties
@Slf4j
public class CalculateAutoConfigure {

  @Autowired
  private CalculateProperties calculateProperties;

  @Bean
  // enable when missing the bean in Spring context
  @ConditionalOnMissingBean // so that people can create bean and do not use this default bean
  // enable when calculate.enabled=true
  @ConditionalOnProperty(prefix = "calculate", value = "enabled", havingValue = "true")
  public CalculateService calculateService() {
    // take properties value from properties file
    log.info(">>>>>>>> >>>>>>>> >>>>>>>> >>>>>>>> {}", calculateProperties.getScale());
    return new CalculateServiceImpl(calculateProperties.getScale());
  }
}
