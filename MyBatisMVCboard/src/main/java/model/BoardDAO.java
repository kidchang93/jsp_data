package model;

import mybatis.mapper.BoardMapper;
import mybatis.factory.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {

    public int selectCount(Map<String, Object> map) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 selectCount 메서드 호출
        int result = mapper.selectCount(map);
        System.out.println("selectCount - 행 개수 = " + result);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return result;


    }
    // 사용하지 않는 메서드임.
    public List<BoardVO> selectListPage(Map<String, Object> map) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 selectListPage 메서드 호출
        List<BoardVO> result = mapper.selectListPage(map);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return result;
    }

    public int insertWrite(BoardVO vo) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 insertWrite 메서드 호출
        int result = mapper.insertWrite(vo);
        // 결과가 1이면(작성 성공), 트랜잭션 커밋 및 메시지 출력
        if (result == 1) {
            sqlSession.commit();
            System.out.println("새로운 board 저장 성공");
        } else {
            // 작성 실패 시, 오류 메시지 출력
            System.out.println("새로운 board 저장 실패");
        }
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return result;
    }

    public List<BoardVO> getListWithPaging(Map<String, Object> map) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 getListWithPaging 메서드 호출
        List<BoardVO> result = mapper.getListWithPaging(map);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // result 값 반환
        return result;
    }

    public BoardVO selectView(String idx) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        BoardVO vo = mapper.selectView(idx);
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
        // vo 반환
        return vo;
    }

    // 특정 게시물의 조회수를 증가시키는 메서드
    public void updateVisitCount(String idx) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 updateVisitCount 메서드 호출
        int result = mapper.updateVisitCount(idx);
        System.out.println("update query result val = " + result);
        // 쿼리 실행 결과가 1이면(업데이트 성공), 트랜잭션 커밋
        if (result == 1) {
            sqlSession.commit();
        } else {
            // 업데이트 실패 시, 오류 메시지 출력
            System.out.println("조회수 증가 중 오류 발생");
        }
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
    }

    public void downCountPlus(String idx) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 downCountPlus 메서드 호출
        int result = mapper.downCountPlus(idx);
        System.out.println("downCountPlus - update query result val = " + result);
        // 쿼리 실행 결과가 1이면(업데이트 성공), 트랜잭션 커밋
        if (result == 1) {
            sqlSession.commit();
        } else {
            // 업데이트 실패 시, 오류 메시지 출력
            System.out.println("다운로드 횟수 1증가 중 오류 발생");
        }
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.close();
    }

    public boolean confirmPassword(String pass, String idx) {
        // 비밀번호와 게시물 인덱스를 매개변수로 하는 맵 생성
        Map<String, String> map = new HashMap<>();
        map.put("pass", pass);
        map.put("idx", idx);
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 confirmPassword 메서드 호출
        int result = mapper.confirmPassword(map);
        // 결과가 1이면(비밀번호 일치), true 반환
        if (result == 1) {
            return true;
        } else {
            // 일치하지 않으면 false 반환
            return false;
        }
    }

    public int deletePost(String idx) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 deletePost 메서드 호출
        int result = mapper.deletePost(idx);
        // 쿼리 실행 결과가 1이면(업데이트 성공), 트랜잭션 커밋
        if (result == 1) {
            sqlSession.commit();
        } else {
            // 업데이트 실패 시, 오류 메시지 출력
            System.out.println("board 삭제 중 오류 발생...");
        }
        return result;
    }

    public int updatePost(BoardVO vo) {
        // MyBatis의 SqlSession 객체 생성
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        // BoardMapper 인터페이스를 구현한 매퍼 인스턴스 생성
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        // Mapper에서 정의한 updatePost 메서드 호출
        int result = mapper.updatePost(vo);
        // 쿼리 실행 결과가 1이면(업데이트 성공), 트랜잭션 커밋
        if (result == 1) {
            sqlSession.commit();
        } else {
            // 업데이트 실패 시, 오류 메시지 출력
            System.out.println("board update 중 오류 발생...");
        }
        // 예외 발생 여부와 관계 없이 항상 SqlSession 닫기
        sqlSession.commit();
        // result 값 반환
        return result;
    }
}
