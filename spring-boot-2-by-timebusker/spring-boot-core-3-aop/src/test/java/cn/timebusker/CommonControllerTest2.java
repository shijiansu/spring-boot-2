package cn.timebusker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
@ActiveProfiles({"dev", "test2"})
public class CommonControllerTest2 {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void givenPath_whenGet_thenReturn200() {
    ResponseEntity<String> response = this.restTemplate.getForEntity("/user", String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

}

