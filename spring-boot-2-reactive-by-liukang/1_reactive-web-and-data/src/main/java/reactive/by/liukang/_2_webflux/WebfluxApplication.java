package reactive.by.liukang._2_webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;
import reactive.by.liukang._2_webflux._4_sse_event.EventConfiguration;

@SpringBootApplication
public class WebfluxApplication {

  public static void main(String[] args) {
    // SpringApplication.run(Application.class, args);
    SpringApplication app = new SpringApplication(WebfluxApplication.class);
    app.addListeners(new ApplicationPidFileWriter()); // pid file
    app.run(args);
  }
}
