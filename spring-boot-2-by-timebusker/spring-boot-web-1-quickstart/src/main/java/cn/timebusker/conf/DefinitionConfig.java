package cn.timebusker.conf;

import java.io.Serializable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义属性与加载:只需要使用@Component注解将类注册到容器内就可以方便使用
 */
@Component
@Data
public class DefinitionConfig implements Serializable {

  private static final long serialVersionUID = 7063410079055294317L;

  @Value("${cn.timebusker.name}")
  private String name;

  @Value("${cn.timebusker.title}")
  private String title;

  @Value("${cn.timebusker.desc}")
  private String desc;

  @Value("${cn.timebusker.value}")
  private String value;

  @Value("${cn.timebusker.number}")
  private Integer number;

  @Value("${cn.timebusker.bignumber}")
  private Long bignumber;

  @Value("${cn.timebusker.random1}")
  private Integer random1;

  @Value("${cn.timebusker.random2}")
  private Integer random2;

}
