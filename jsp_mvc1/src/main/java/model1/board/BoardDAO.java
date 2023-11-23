package model1.board;

import common.JDBConnect;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BoardDAO extends JDBConnect {
  //application 내장 객체를 통해 JDBConnect 생성자에서 web.xml에 정의해둔 mariadb 접속정보를 직접 가져와 DB에 연결
  public BoardDAO(ServletContext application) {
    super(application);
  }

  //검색 조건에 맞는 게시물의 개수를 반환합니다.
  public int selectCount(Map<String, Object> map) {
    int totalCount = 0; // 결과(게시물 수)를 담을 변수

    //게시물 수를 얻어오는 쿼리문 작성
    String query = "SELECT COUNT(*) FROM board";
    if (map.get("searchWord") != null) {
      query += " WHERE " + map.get("searchField") + " "
              + " LIKE '%" + map.get("searchWord") + "%'";
    }

    try {
      stmt = con.createStatement(); // 쿼리문 생성
      rs = stmt.executeQuery(query); //쿼리실행
      rs.next(); //커서를 첫번째 행으로 이동
      totalCount = rs.getInt(1); //select절에 명시된 컬럼의 인덱스, 1부터 시작이므로 첫번째 컬럼 값을 가져옴
    } catch (Exception e) { //jdbc 프로그래밍은 기본적으로 예외처리를 해야됨
      System.out.println("게시물 수를 구하는 중 예외 발생");
      e.printStackTrace();
    }
    return totalCount;
  }

  public List<BoardDTO> selectList(Map<String, Object> map) {
    //결과(게시물 목록을 담을 변수)
    // 테이블에서 레코드를 가져올때는 항상 List 계열의 컬렉션에 저장. 순서대로 저장되어 있어 인덱스를 통해 가져올수 있기때문
    //Vector 이외에도 List 계열의 컬렉션(ArrayList, LinkedList)이라면 모두 사용 가능
    List<BoardDTO> bbs = new Vector<BoardDTO>();

    String query = "SELECT * FROM board ";
    if (map.get("searchWord") != null) {
      query += " WHERE " + map.get("searchField") + " "
              + " LIKE '%" + map.get("searchWord") + "%' ";
    }
    query += " ORDER BY num DESC ";

    try {
      stmt = con.createStatement(); // 쿼리문 생성
      rs = stmt.executeQuery(query); // 쿼리실행

      while (rs.next()) { //결과 result set순회
        BoardDTO dto = new BoardDTO();

        dto.setNum(rs.getString("num")); //일련번호
        dto.setTitle(rs.getString("title")); //제목
        dto.setContent(rs.getString("content")); //내용
        dto.setPostdate(rs.getDate("postdate")); //작성일
        dto.setId(rs.getString("id")); //작성자 아이디
        dto.setVisitcount(rs.getString("visitcount")); //조회수

        bbs.add(dto); // 결과 목록에 저장
      }
    } catch (Exception e) {
      System.out.println("게시물 조회 중 예외 발생");
      e.printStackTrace();
    }
    return bbs;
  }

  // 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
  public List<BoardDTO> selectListPage(Map<String, Object> map) {
    List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수

    // 쿼리문 템플릿
    String query =" SELECT tb.* FROM ( "
            + " SELECT * FROM board ";

    // 검색 조건 추가
    if (map.get("searchWord") != null) {
      query += " WHERE " + map.get("searchField")
              + " LIKE '%" + map.get("searchWord") + "%' ";
    }

    query += " ) tb " +
            " ORDER BY num DESC " +
            " LIMIT ?, 10; ";

    try {
      // 쿼리문 완성
      psmt = con.prepareStatement(query);
      psmt.setInt(1, Integer.parseInt(String.valueOf(map.get("start"))));
//      psmt.setString(2, map.get("end").toString());

      // 쿼리문 실행
      rs = psmt.executeQuery();

      while (rs.next()) {
        // 한 행(게시물 하나)의 데이터를 DTO에 저장
        BoardDTO dto = new BoardDTO();
        dto.setNum(rs.getString("num"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        dto.setPostdate(rs.getDate("postdate"));
        dto.setId(rs.getString("id"));
        dto.setVisitcount(rs.getString("visitcount"));

        // 반환할 결과 목록에 게시물 추가
        bbs.add(dto);
      }
    }
    catch (Exception e) {
      System.out.println("게시물 조회 중 예외 발생");
      e.printStackTrace();
    }

    // 목록 반환
    return bbs;
  }


  // 게시글 데이터를 받아 DB에 추가합니다.
  public int insertWrite(BoardDTO dto) { //BoardDTO 타입의 매개변수를 받음
    int result = 0;

    try {
      // 데이터 INSERT 쿼리문 작성
      String query = "INSERT INTO board ( "
              + " title,content,id,visitcount) "
              + " VALUES ( "
              + " ?, ?, ?, 0)";

      psmt = con.prepareStatement(query);  // 동적 쿼리
      psmt.setString(1, dto.getTitle());
      psmt.setString(2, dto.getContent());
      psmt.setString(3, dto.getId());

      result = psmt.executeUpdate(); //insert 성공한 행의 개수를 정수로 반환
    } catch (Exception e) {
      System.out.println("게시물 입력 중 예외 발생");
      e.printStackTrace();
    }

    return result;
  }
  // 지정한 게시물을 찾아 내용을 반환합니다.
  public BoardDTO selectView(String num) { // JSP에서 num에 일련번호 받기
    BoardDTO dto = new BoardDTO();

    // 쿼리문 준비
    String query = "SELECT B.*, M.name " //이름을 출력하기 위해서는 member 테이블에서 조인으로 가져와야됨
            + " FROM member M INNER JOIN board B "
            + " ON M.id=B.id "
            + " WHERE num=?";

    try {
      psmt = con.prepareStatement(query);
      psmt.setString(1, num);    // 인파라미터(?물음표)를 일련번호로 설정
      rs = psmt.executeQuery();  // 쿼리 실행

      // 결과 처리
      if (rs.next()) { //ResultSet 객체로 반환된 행을 next() 메서드로 확인
        dto.setNum(rs.getString(1)); //DTO 객체에 저장
        dto.setTitle(rs.getString(2));
        dto.setContent(rs.getString("content"));
        dto.setPostdate(rs.getDate("postdate"));
        dto.setId(rs.getString("id"));
        dto.setVisitcount(rs.getString(6));
        dto.setName(rs.getString("name"));
      }
    }
    catch (Exception e) {
      System.out.println("게시물 상세보기 중 예외 발생");
      e.printStackTrace();
    }

    return dto; //완성된 DTO 반환
  }

  // 지정한 게시물의 조회수를 1 증가시킵니다.
  public void updateVisitCount(String num) {// JSP에서 num에 조회수 증가시킬 일련번호 받기
    // 쿼리문 준비
    String query = "UPDATE board SET "
            + " visitcount=visitcount+1 " //visitcount 컬럼 +1
            + " WHERE num=?";

    try {
      psmt = con.prepareStatement(query);
      psmt.setString(1, num);  // 인파라미터를 일련번호로 설정
      psmt.executeQuery();     // 쿼리 실행
    }
    catch (Exception e) {
      System.out.println("게시물 조회수 증가 중 예외 발생");
      e.printStackTrace();
    }
  }

  // 지정한 게시물을 수정합니다.
  public int updateEdit(BoardDTO dto) {
    int result = 0;

    try {
      // 쿼리문 템플릿
      String query = "UPDATE board SET "
              + " title=?, content=? "
              + " WHERE num=?";

      // 쿼리문 완성
      psmt = con.prepareStatement(query);
      psmt.setString(1, dto.getTitle()); //수정할 제목 인파라미터에 set
      psmt.setString(2, dto.getContent()); //수정할 내용 인파라미터에 set
      psmt.setString(3, dto.getNum()); //필터기준인 수정대상 게시물 일련번호

      // 쿼리문 실행
      result = psmt.executeUpdate(); //반환값은 업데이트된 행의 개수
    }
    catch (Exception e) {
      System.out.println("게시물 수정 중 예외 발생");
      e.printStackTrace();
    }

    return result; // 결과 반환
  }
  // 지정한 게시물을 삭제합니다.
  public int deletePost(BoardDTO dto) { //삭제할 게시물의 일련번호와 로그인 아이디를(그 외에도 다른 정보도 들어옴) 담은 DTO 객체를 매개변수로 받음
    int result = 0;

    try {
      // 쿼리문 템플릿
      String query = "DELETE FROM board WHERE num=?";

      // 쿼리문 완성
      psmt = con.prepareStatement(query);
      psmt.setString(1, dto.getNum()); //인파라미터로 일련번호를 설정

      // 쿼리문 실행
      result = psmt.executeUpdate(); //삭제한 행의 개수반환
    }
    catch (Exception e) {
      System.out.println("게시물 삭제 중 예외 발생");
      e.printStackTrace();
    }

    return result; // 결과 반환
  }
}
