package mybatis.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TestDTO {

    private int id;
    private String name;
    private int age; // default 0
    private LocalDateTime today; // default null
}