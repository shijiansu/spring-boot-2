package reactive.webclient.by.stackabuse;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
  Logger logger = LoggerFactory.getLogger(PersonController.class);

  private static List<Person> personList = new ArrayList<>();

  static {
    personList.add(new Person(1, "John"));
    personList.add(new Person(2, "Jane"));
    personList.add(new Person(3, "Max"));
    personList.add(new Person(4, "Alex"));
    personList.add(new Person(5, "Aloy"));
    personList.add(new Person(6, "Sarah"));
  }

  @GetMapping("/person/{id}")
  public Person person(@PathVariable int id, @RequestParam(defaultValue = "2") int delay)
      throws InterruptedException {
    Thread.sleep(delay * 1000);
    logger.info("getting id: {}", id);
    return personList.stream().filter((person) -> person.getId() == id).findFirst().orElse(null);
  }

  @GetMapping("/person/{id}/hobby")
  public Hobby personHobby(@PathVariable int id, @RequestParam(defaultValue = "1") int delay)
      throws InterruptedException {
    Thread.sleep(delay * 1000);
    logger.info("getting hobby: {}", id);
    return new Hobby(id, "Hobby of " + id);
  }
}
