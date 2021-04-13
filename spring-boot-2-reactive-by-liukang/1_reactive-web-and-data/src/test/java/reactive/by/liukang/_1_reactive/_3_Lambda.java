package reactive.by.liukang._1_reactive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class _3_Lambda {
  /**
   * 1. 响应式之道 - 3 快速上手 - 1 lambda与函数式
   *
   * <p>对Student进行排序的例子。
   */
  @Test
  public void StudentCompareTest() {
    @Data
    @AllArgsConstructor
    class Student {
      int id;
      String name;
      double height;
      double score;
    }

    List<Student> students = new ArrayList<>();
    students.add(new Student(10001, "张三", 1.73, 88));
    students.add(new Student(10002, "李四", 1.71, 96));
    students.add(new Student(10003, "王五", 1.85, 88));

    // 1. 通过实现类定义
    class StudentIdComparator<S extends Student> implements Comparator<S> {
      @Override
      public int compare(S s1, S s2) {
        return Integer.compare(s1.getId(), s2.getId());
      }
    }

    students.sort(new StudentIdComparator<>());
    System.out.println(students);

    // 2. 通过匿名内部类定义
    students.sort(
        new Comparator<Student>() {
          @Override
          public int compare(Student o1, Student o2) {
            return Double.compare(o1.getHeight(), o2.getHeight());
          }
        });
    System.out.println(students);

    // 3. 通过lambda定义
    students.sort((Student o1, Student o2) -> Double.compare(o1.getHeight(), o2.getHeight()));
    System.out.println(students);

    // 3.1 简化版lambda
    students.sort(Comparator.comparingDouble(Student::getScore));
    System.out.println(students);
  }
}
