package Board;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int idx;
    private String name;
    private String title;
    private String content;
    private Date postDate;
    private String ofile;
    private String sfile;
    private int downCount;
    private String pass;
    private int visitCount;
}
