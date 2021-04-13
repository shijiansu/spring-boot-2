package spring.boot._2.test.by.howtodoinjava.mvc.webmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("slicing")
@WebMvcTest(EmployeeController.class)
public class _1_EmployeeControllerSlicingTest {

  @Autowired MockMvc client;

  @Autowired ObjectMapper mapper;

  @SneakyThrows
  @Test
  public void create() {
    client
        .perform(
            MockMvcRequestBuilders.post("/create")
                .content(mapper.writeValueAsString(new Employee(null, "Name 4", 4000)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }

  @SneakyThrows
  @Test
  public void update() {
    client
        .perform(
            MockMvcRequestBuilders.put("/update")
                .content(mapper.writeValueAsString(new Employee(4, "New Name", 4000)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Name"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(4000));
  }

  @SneakyThrows
  @Test
  public void employee() {
    client
        .perform(MockMvcRequestBuilders.get("/{id}", 1).accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
  }

  @SneakyThrows
  @Test
  public void employees() {
    client
        .perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].id").isNotEmpty());
  }

  @Test
  public void delete() throws Exception {
    client
        .perform(MockMvcRequestBuilders.delete("/delete/{id}", 1))
        .andExpect(status().isAccepted());
  }

  @Test
  public void testHelloWorldController() throws Exception {
    MvcResult mvcResult =
        client
            .perform(MockMvcRequestBuilders.get("/future"))
            .andExpect(request().asyncStarted())
            .andDo(MockMvcResultHandlers.log())
            .andReturn();
    client
        .perform(asyncDispatch(mvcResult)) // need to wrap with asyncDispatch()
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("Hello Future!"));
  }
}
