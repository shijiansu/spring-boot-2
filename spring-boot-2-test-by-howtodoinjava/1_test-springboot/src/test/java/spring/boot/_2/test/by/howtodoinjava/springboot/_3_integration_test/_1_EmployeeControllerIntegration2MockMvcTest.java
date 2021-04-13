package spring.boot._2.test.by.howtodoinjava.springboot._3_integration_test;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spring.boot._2.test.by.howtodoinjava.springboot.Employee;

@ActiveProfiles("integration")
@SpringBootTest
@AutoConfigureMockMvc // for MockMvc
public class _1_EmployeeControllerIntegration2MockMvcTest {
  // 发送请求
  @Autowired MockMvc client;

  @Autowired ObjectMapper mapper;

  @SneakyThrows
  @Sql({"data/truncate.sql"})
  @Test
  public void create() {
    client
        .perform(
            MockMvcRequestBuilders.post("/create")
                .content(mapper.writeValueAsString(new Employee(1, "Name 1", 1000)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(
            MockMvcResultMatchers.header()
                // use URI toASCIIString because .stringValues directly get HTTP header string
                // without decoding; Using endsWith to ignore the domain name in Location
                .string("Location", endsWith(new URI("/create/1").toASCIIString())));
  }
}
