<%@ page import="model1.board.BoardDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    // DAO를 생성해 DB에 연결
    BoardDAO dao = new BoardDAO(application);

    //사용자가 입력한 검색 조건을 Map에 저장
    Map<String , Object> param = new HashMap<String, Object>();

    String searchField = request.getParameter("searchField");
    String searchWord = request.getParameter("searchWord");
    if (searchWord != null) {
      param.put("searchField", searchField);
      param.put("searchWord", searchWord);
    }

    int totalCount = dao.selectCount(param); // 게시물 수 확인
    List<BoardDTO> boardLists = dao.selectList(param); // 게시물 목록 받기
    dao.close(); // DB 연결 닫기
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원제 게시판</title>
</head>
<body>

</body>
</html>
