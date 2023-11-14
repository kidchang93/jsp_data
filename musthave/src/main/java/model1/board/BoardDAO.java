package model1.board;

import common.JDBConnect;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BoardDAO extends JDBConnect {
  public BoardDAO(ServletContext application){
      super(application);
  }
  //----------------------------------------------------------------------------------- DAO
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

  //----------------------------------------------------------------------------------- DTO
  // 검색 조건에 맞는 게시물 목록을 반환합니다.
public List<BoardDTO> selectList(Map<String, Object> map){
    List<BoardDTO> bbs = new Vector<BoardDTO>();
                                // 결과(게시물 목록)를 담을 변수

  String query = "SELECT * FROM board";
  if (map.get("searchWord") != null) {
    query += " WHERE " + map.get("searchField") + " "
            + " LIKE '%" + map.get("searchWord") + "%' ";
    query += " ORDER BY num DESC";

    try {
      stmt = con.createStatement();       // 쿼리문 생성
      rs = stmt.executeQuery(query);      // 쿼리 실행

      while (rs.next()) {                 // 결과를 순회하며...
        // 한 행(게시물 하나)의 내용을 DTO 에 저장
        BoardDTO dto = new BoardDTO();    

        dto.setNum(rs.getString("num"));                  // 일련번호
        dto.setTitle(rs.getString("title"));              // 제목
        dto.setContent(rs.getString("content"));          //내용
        dto.setPostdate(rs.getDate("postdate"));          // 작성일
        dto.setId(rs.getString("id"));                    // 작성자 아이디
        dto.setVisitcount(rs.getString("visitcount"));    //조회수

        bbs.add(dto);                                                 // 결과 목록에 저장
      }
    } catch (Exception e){
      System.out.println("게시물 조회 중 예외 발생");
      e.printStackTrace();
    }
  }
    return bbs;
}

}
