<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HelloServlet.jsp</title>
</head>
<body>
<h2>web.xml에서 매핑 후 JSP에서 출력하기</h2>
<p>
    <Strong><%= request.getAttribute("message") %></Strong>
    <br>
    <a href="./HelloServlet.do">바로가기</a>
</p>
</body>
</html>
