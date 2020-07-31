package spring.boot._2.by.howtodoinjava._9;

import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(DataSetController.class)
public class DataSetControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private DataSetService dataSetService;

  @Test
  public void fetch() throws Exception {
    Mockito.when(dataSetService.findAll())
        .thenReturn(singletonList(new DataSet(BigInteger.valueOf(1), "data")));

    MvcResult mvcResult =
        mockMvc
            .perform(get("/emit-data-sets"))
            .andExpect(request().asyncStarted())
            .andDo(log())
            .andReturn();

    mockMvc
        .perform(asyncDispatch(mvcResult))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/event-stream;charset=UTF-8"));

    String event = mvcResult.getResponse().getContentAsString();
    event = event.replaceAll("data:", "");
    event = event.replaceAll("\\n", "");

    new JsonPathExpectationsHelper("$.id").assertValue(event, "1");
    new JsonPathExpectationsHelper("$.name").assertValue(event, "data");
  }
}
