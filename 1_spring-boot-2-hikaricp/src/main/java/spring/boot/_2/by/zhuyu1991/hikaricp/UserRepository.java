package spring.boot._2.by.zhuyu1991.hikaricp;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  User findById(int id);

  User findByName(String name);

  void deleteById(int id);

  // 自定义sql
  @Modifying
  @Query(value = "select * from user", nativeQuery = true)
  List<User> findAll();
}
