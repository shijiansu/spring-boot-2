package spring.boot._2.test.by.howtodoinjava.springboot._2_unit_test_with_springboottest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/** Using @SpringBootTest for unit test. Be careful of object dependencies */
@ActiveProfiles("unit")
@SpringBootTest
@Import(MyTestConfiguration.class)
public class _1_EmployeeController2Test extends _1_EmployeeController0NotExecutableTest {
  // For the helper, as there is no auto scan, so it refer to byName.
  // If in MyTestConfiguration it is not name the bean as "helper",
  // then will be error as no bean defined
}
