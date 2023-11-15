<%@ page import="model1.board.BoardDAO" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String num = request.getParameter("num"); // 일련번호 받기

    BoardDAO dao = new BoardDAO(application); // DAO 생성
    dao.updateVisitCount(num);                // 조회수 증가
    BoardDTO dto = dao.selectView(num);       // 게시물 가져오기
    dao.close();                              // DB 연결 해제
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원제 게시판</title>
    <script>
        function deletePost(){
            // 생략(8.7에서 할거임)
        }
    </script>
</head>
<body>
<jsp:include page="../Common/Link.jsp"/> <!-- 공통 링크 -->
<h2>회원제 게시판 - 상세보기(View)</h2>
<form name="writeFrm">
    <input type="hidden" name="num" value="<%= num %>"/>
    <table border="1" width="90%">
    <tr>
        <td>번호</td>
        <td><%= dto.getNum() %></td>
        <td>작성자</td>
        <td><%= dto.getName() %></td>
    </tr>
        <tr>
            <td>작성일</td>
            <td><%= dto.getPostdate() %></td>
            <td>조회수</td>
            <td><%= dto.getVisitcount() %></td>
        </tr>
        <tr>
            <td>제목</td>
            <td colspan="3"><%= dto.getTitle() %></td>
        </tr>
        <tr>
            <td>내용</td>
            <td colspan="3" height="100">
            <%= dto.getContent().replace("\r\n", "<br>")%></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <%
                    if (session.getAttribute("UserId") != null
                            && session.getAttribute("UserId").toString().equals(dto.getId())){
                %>


            </td>
        </tr>
    </table>
</form>
</body>
</html>
