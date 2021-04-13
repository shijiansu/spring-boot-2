package spring.boot._2.test.by.howtodoinjava.springboot._2_unit_test_with_springboottest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeHelper;

public class _1_EmployeeController0NotExecutableTest {

  // byName first, then byType.
  @Autowired EmployeeHelper helper;

  @Test
  public void helper() {
    assertNotNull(helper);
    String s = helper.helloWorld();
    assertEquals("Hello world!", s);
  }
}
