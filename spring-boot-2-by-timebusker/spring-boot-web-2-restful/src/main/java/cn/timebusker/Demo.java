package cn.timebusker;

import java.io.Serializable;
import lombok.Data;

@Data
public class Demo implements Serializable {

  private static final long serialVersionUID = -1727537778826379384L;

  private String id;

  private String name;

  private String description;

}
