package model;

import mybatis.mapper.BoardMapper;
import mybatis.factory.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {

    public int selectCount(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.selectCount(map);
        System.out.println("selectCount - 행 개수 = " + result);
        sqlSession.close();
        return result;


    }
    public List<BoardVO> selectListPage(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        List<BoardVO> result = mapper.selectListPage(map);
        sqlSession.close();
        return result;
    }

    public int insertWrite(BoardVO dto) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.insertWrite(dto);
        if (result == 1) {
            sqlSession.commit();
            System.out.println("새로운 mvcboard 저장 성공");
        } else {
            System.out.println("새로운 mvcboard 저장 실패");
        }
        sqlSession.close();
        return result;
    }

    public List<BoardVO> selectListPageWithPaging(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        List<BoardVO> result = mapper.selectListPageWithPaging(map);
        sqlSession.close();
        return result;
    }

    public BoardVO selectView(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        BoardVO dto = mapper.selectView(idx);
        sqlSession.close();
        return dto;
    }

    public void updateVisitCount(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.updateVisitCount(idx);
        System.out.println("update query result val = " + result);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("조회수 증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public void downCoundPlus(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.downCountPlus(idx);
        System.out.println("downCountPlus - update query result val = " + result);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("다운로드 횟수 1증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public boolean confirmPassword(String pass, String idx) {
        Map<String, String> map = new HashMap<>();
        map.put("pass", pass);
        map.put("idx", idx);
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.confirmPassword(map);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int deletePost(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.deletePost(idx);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("board 삭제 중 오류 발생...");
        }
        return result;
    }

    public int updatePost(BoardVO dto) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = mapper.updatePost(dto);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("board update 중 오류 발생...");
        }
        sqlSession.commit();
        return result;
    }
}
