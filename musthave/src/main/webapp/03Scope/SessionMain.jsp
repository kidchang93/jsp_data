<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<%
    ArrayList<String> lists = new ArrayList<String>();
    lists.add("리스트");
    lists.add("컬렉션");
    session.setAttribute("lists",lists);
%>
<html>
<head>
    <title>Session 영역</title>
</head>
<body>
<h2>페이지 이동 후 session 영역의 속성 읽기</h2>
<a href="SessionLocation.jsp">SessionLocation.jsp 바로가기</a>
</body>
</html>
