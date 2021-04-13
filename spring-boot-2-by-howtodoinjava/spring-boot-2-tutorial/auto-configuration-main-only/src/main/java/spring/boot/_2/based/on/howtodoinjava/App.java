package spring.boot._2.based.on.howtodoinjava;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(App.class, args);

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      System.out.println(beanName + " # " + ctx.getBean(beanName).getClass().getName());
    }
    SpringApplication.exit(ctx);
  }
}
