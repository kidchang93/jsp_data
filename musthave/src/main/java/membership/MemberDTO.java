package membership;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MemberDTO {
  // 멤버 변수 선언
  private String id;
  private String pass;
  private String name;
  private String register;
}
