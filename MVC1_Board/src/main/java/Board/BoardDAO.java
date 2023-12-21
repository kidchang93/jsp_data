package Board;

import mybatis.factory.MyBatisSessionFactory;
import mybatis.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDAO {

    public List<BoardDTO> selectList (Map<String, Object>map){


        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 selectCount 메서드 호출
        List<BoardDTO> result = mapper.selectList(map);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return result;
    }
    public BoardDTO selectView(String idx){

        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 selectCount 메서드 호출
        BoardDTO dto = mapper.selectView(idx);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return dto;
    }

}
