package spring.boot._2.by.howtodoinjava._5;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  public Employee(Integer id, String firstName, String lastName, String email) {

    this.employeeId = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Employee() {}

  private Integer employeeId;

  @NotEmpty(message = "first name must not be empty")
  private String firstName;

  @NotEmpty(message = "last name must not be empty")
  private String lastName;

  @NotEmpty(message = "email must not be empty")
  @Email(message = "email should be a valid email")
  private String email;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
