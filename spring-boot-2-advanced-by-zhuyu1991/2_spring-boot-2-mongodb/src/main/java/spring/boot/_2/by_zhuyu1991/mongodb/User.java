package spring.boot._2.by_zhuyu1991.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private int age;
  private String name;
}
