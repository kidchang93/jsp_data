<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<table border="1" width="90%">
    <tr>
        <td align="center">
            <!-- 로그인여부에 따른 메뉴변화 -->
            <% if(session.getAttribute("UserId") == null) { %>
                <a href="../Session/LoginForm.jsp">로그인</a>
            <% }else {%>
                <a href="../Session/Logout.jsp">로그아웃</a>
            <% } %>
            <!-- 8장과 9장의 회원제 게시판 프로젝트에서 사용할 링크 -->
            &nbsp;&nbsp;&nbsp; <!-- 메뉴사이의 공백 확보용 특수문자 -->
            <a href="../Board/List.jsp">게시판(페이징x)</a>
            &nbsp;&nbsp;&nbsp; <!-- 메뉴사이의 공백 확보용 특수문자 -->
            <a href="../PagingBoard/List.jsp">게시판(페이징o)</a>
        </td>
    </tr>
</table>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
