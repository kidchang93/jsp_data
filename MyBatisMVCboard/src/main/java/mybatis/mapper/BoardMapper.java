package mybatis.mapper;

import model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    int selectCount(Map<String, Object> map);

    List<BoardVO> selectListPage(Map<String, Object> map);

    int insertWrite(BoardVO dto);

    List<BoardVO> selectListPageWithPaging(Map<String, Object> map);

    BoardVO selectView(String idx);

    int updateVisitCount(String idx);

    int downCountPlus(String idx);

    int confirmPassword(Map<String, String> map);

    int deletePost(String idx);

    int updatePost(BoardVO dto);

}