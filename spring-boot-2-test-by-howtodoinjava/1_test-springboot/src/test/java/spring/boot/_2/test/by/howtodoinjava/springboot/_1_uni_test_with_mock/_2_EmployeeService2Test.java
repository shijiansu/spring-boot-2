package spring.boot._2.test.by.howtodoinjava.springboot._1_uni_test_with_mock;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unit")
// @ExtendWith(MockitoExtension.class) // use MockitoAnnotations.initMocks() instead
public class _2_EmployeeService2Test extends _2_EmployeeService0NotExecutableTest {
  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }
}
