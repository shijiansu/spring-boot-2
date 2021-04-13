package spring.boot._2.by.zhuyu1991.hikaricp;

import java.util.List;

public interface UserService {
  void add(User user);

  void edit(User user);

  void delete(int id);

  User findById(int id);

  User findByName(String name);

  List<User> findAll();
}
