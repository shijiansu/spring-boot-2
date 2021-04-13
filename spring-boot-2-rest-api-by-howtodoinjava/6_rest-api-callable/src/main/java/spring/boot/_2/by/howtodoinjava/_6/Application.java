package spring.boot._2.by.howtodoinjava._6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// To override the default async behavior such as thread pool and timeout,
// you can implement the WebMvcConfigurer interface
// and override it’s configureAsyncSupport() method.
@SpringBootApplication
public class Application implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.addListeners(new ApplicationPidFileWriter()); // pid file
    app.run(args);
  }

  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    configurer.setTaskExecutor(mvcTaskExecutor());
    configurer.setDefaultTimeout(30_000);
  }

  @Bean
  public ThreadPoolTaskExecutor mvcTaskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setMaxPoolSize(10);
    taskExecutor.setThreadNamePrefix("mvc-task-");
    return taskExecutor;
  }
}
