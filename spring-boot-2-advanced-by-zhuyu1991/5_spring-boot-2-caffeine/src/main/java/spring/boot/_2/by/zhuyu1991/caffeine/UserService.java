package spring.boot._2.by.zhuyu1991.caffeine;

public interface UserService {

  String find(String name);

  User find(User user);

  User findEven(User user);

  void delete();

  void delete(int id);

  User put(User user);

  User findByAnnotation(User user);
}
