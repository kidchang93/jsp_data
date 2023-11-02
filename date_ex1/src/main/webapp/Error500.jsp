<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-01
  Time: 오전 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"
         errorPage="IsErrorPage.jsp"
%>


<html>
<head>
    <title>지시어 - errorPage , isErrorPage 속성</title>
    <meta charset ="UTF-8">
</head>
<body>
<%
    int myAge = Integer.parseInt(request.getParameter("age")) + 10 ;
    out.println("10년 후 당신의 나이는 "+myAge+"입니다.");
%>
</body>
</html>
