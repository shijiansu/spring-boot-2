package spring.boot._2.by_zhuyu1991.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class _1_MongodbManualTest {
  @Autowired private MongoTemplate mongoTemplate;

  String collectionName = "user";

  @Order(1)
  @Test
  public void add() {
    Query query = new Query(Criteria.where("id").is(1));
    mongoTemplate.remove(query, collectionName);

    User user = new User();
    user.setId(1);
    user.setName("zhuyu");
    user.setAge(26);
    User user1 = mongoTemplate.insert(user, collectionName);
    assertEquals(user.getId(), user1.getId());
    assertEquals(user.getName(), user1.getName());
    assertEquals(user.getAge(), user1.getAge());
  }

  @Order(2)
  @Test
  public void edit() {
    Query query = new Query(Criteria.where("name").is("zhuyu"));
    Update update = new Update();
    update.set("name", "zy");
    update.set("age", 24);
    UpdateResult user = mongoTemplate.updateFirst(query, update, collectionName);
    // mongoTemplate.updateMulti(query,update,User.class); // update all
    assertEquals(1, user.getModifiedCount());
  }

  @Order(3)
  @Test
  public void get_by_value() {
    Query query = new Query(Criteria.where("name").is("zy"));
    User user = mongoTemplate.findOne(query, User.class, collectionName);
    log.info(JSON.toJSONString(user));
    assertEquals("zy", user.getName());
  }

  @Order(4)
  @Test
  public void delete() {
    Query query = new Query(Criteria.where("name").is("zy"));
    DeleteResult remove = mongoTemplate.remove(query, collectionName);
    assertEquals(1, remove.getDeletedCount());
  }
}
