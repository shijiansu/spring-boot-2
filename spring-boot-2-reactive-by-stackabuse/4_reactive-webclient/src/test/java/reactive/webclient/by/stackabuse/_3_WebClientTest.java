package reactive.webclient.by.stackabuse;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class _3_WebClientTest extends _2_WebClientManualTest {
  // bind the above RANDOM_PORT
  @LocalServerPort int port;

  @Override
  public String baseUrl() {
    return "http://localhost:" + port;
  }
}
