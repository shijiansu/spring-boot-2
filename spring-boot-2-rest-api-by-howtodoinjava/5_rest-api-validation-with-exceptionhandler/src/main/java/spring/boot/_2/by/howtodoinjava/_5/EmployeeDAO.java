package spring.boot._2.by.howtodoinjava._5;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

  private static Employees list = new Employees();

  static {
    list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
    list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
    list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
  }

  public Employees getAllEmployees() {
    return list;
  }

  public void addEmployee(Employee employee) {
    employee.setEmployeeId(list.getEmployeeList().size()+1);
    list.getEmployeeList().add(employee);
  }
}
