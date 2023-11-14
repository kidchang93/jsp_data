package model1.board;

import common.JDBConnect;

import javax.servlet.ServletContext;
import java.util.Map;

public class BoardDAO extends JDBConnect {
  public BoardDAO(ServletContext application){
      super(application);
  }

  // 검색 조건에 맞는 게시물의 개수를 반환 합니다.
  public int selectCount(Map<String, Object> map) {
    int totalCount = 0; // 결과(게시물 수)를 담을 변수

    // 게시물 수를 얻어오는 쿼리문 작성
    String query = "SELECT COUNT(*) FROM board";
    if (map.get("searchWord") != null){
      query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
    }

    try {
      stmt = con.createStatement(); // 쿼리문 생성
      rs = stmt.executeQuery(query); // 쿼리 실행
      rs.next();                      // 커서를 첫번째 행으로 이동
      totalCount = rs.getInt(1); // 첫번째 컬럼 값을 가져옴
    } catch (Exception e) {
      System.out.println("게시물 수를 구하는 중 예외 발생");
      e.printStackTrace();
    }

    return totalCount;
  }


}