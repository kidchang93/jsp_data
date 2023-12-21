package mybatis.mapper;


import Board.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    public List<BoardDTO> selectList(Map<String, Object> map);
    public BoardDTO selectView(String idx);
}