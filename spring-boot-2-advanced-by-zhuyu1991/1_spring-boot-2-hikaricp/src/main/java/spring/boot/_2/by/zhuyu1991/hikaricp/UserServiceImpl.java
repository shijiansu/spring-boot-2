package spring.boot._2.by.zhuyu1991.hikaricp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;

  @Override
  public void add(User user) {
    userRepository.save(user);
  }

  @Override
  public void edit(User user) {
    userRepository.save(user);
  }

  @Override
  public void delete(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public User findById(int id) {
    return userRepository.findById(id);
  }

  @Override
  public User findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
