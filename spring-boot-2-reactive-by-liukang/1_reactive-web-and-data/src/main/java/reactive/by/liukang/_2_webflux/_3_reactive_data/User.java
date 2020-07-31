package reactive.by.liukang._2_webflux._3_reactive_data;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
  @Id private String id;

  @Indexed(unique = true)
  private String username;

  private String name;
  private String phone;
  private Date birthday;
}
