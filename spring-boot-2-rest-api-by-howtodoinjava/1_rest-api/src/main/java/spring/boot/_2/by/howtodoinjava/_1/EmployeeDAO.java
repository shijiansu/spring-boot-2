package spring.boot._2.by.howtodoinjava._1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

  Logger l = LoggerFactory.getLogger(EmployeeDAO.class);

  private static Employees list = new Employees();

  public EmployeeDAO() {
    list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
    list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
    list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
  }

  public Employees getAllEmployees() {
    l.info("employees: {}", list);
    return list;
  }

  public boolean addEmployee(Employee employee) {
    return list.getEmployeeList().add(employee);
  }
}
