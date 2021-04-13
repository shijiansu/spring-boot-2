package spring.boot._2.based.on.howtodoinjava;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot2DemoApplication implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private
  EmployeeRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2DemoApplication.class, args);
  }

  // The run() method from CommandLineRunner is executed immediately after the application startup
  @Override
  public void run(String... args) throws Exception {
    Optional<EmployeeEntity> emp = repository.findById(2L);

    logger.info("Employee id 2 -> {}", emp.orElse(null));
  }
}
