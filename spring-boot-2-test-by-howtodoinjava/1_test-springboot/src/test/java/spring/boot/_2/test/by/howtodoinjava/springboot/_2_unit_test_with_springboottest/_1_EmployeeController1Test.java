package spring.boot._2.test.by.howtodoinjava.springboot._2_unit_test_with_springboottest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeHelper;

/** Using @SpringBootTest for unit test. Be careful of object dependencies */
@ActiveProfiles("unit")
@SpringBootTest(classes = EmployeeHelper.class)
public class _1_EmployeeController1Test extends _1_EmployeeController0NotExecutableTest {}
