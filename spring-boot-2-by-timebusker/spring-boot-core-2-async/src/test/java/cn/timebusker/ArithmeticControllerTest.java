package cn.timebusker;

import static cn.timebusker.ArithmeticService.COUNT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class ArithmeticControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void givenPath_whenGet_thenReturn200() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    ResponseEntity<String> response = this.restTemplate.getForEntity("/", String.class);
    stopWatch.stop();

    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat("Shall not take more than 5500ms", stopWatch.getTotalTimeMillis(), lessThan(5500L));
  }

  @Test
  public void givenMine_whenGet_thenReturn200() throws InterruptedException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    ResponseEntity<String> response = this.restTemplate.getForEntity("/mine", String.class);
    stopWatch.stop();
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    // wait all process are done then closed Spring text context
    TimeUnit.MILLISECONDS.sleep(COUNT);
  }
}

