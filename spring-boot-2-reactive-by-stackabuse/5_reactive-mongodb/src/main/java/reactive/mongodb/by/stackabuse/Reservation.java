package reactive.mongodb.by.stackabuse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Reservation {

  @Id private String id;

  private String reservationName;
}
