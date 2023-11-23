package model1.board;

import lombok.Data;

@Data
public class BoardDTO {
  private String num;
  private String title;
  private String content;
  private String id;
  private java.sql.Date postdate;
  private String visitcount;
  private String name;
}
