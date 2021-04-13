package cn.timebusker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

// Spring Boot includes DiskSpaceHealthIndicator, DataSourceHealthIndicator
@Component
public class MineHealthIndicators implements HealthIndicator {

  private int errorCode = 0;

  @Override
  public Health health() {
    if (checkDataSource() != 0) {
      return Health.down().withDetail("Error Code", errorCode).build();
    }
    return Health.up().build();
  }

  private int checkDataSource() {
    return errorCode;
  }
}
