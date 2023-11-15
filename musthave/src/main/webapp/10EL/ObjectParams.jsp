<%@ page import="common.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<meta charset="UTF-8">
<head>
    <title>표현 언어(EL) - 객체 매개 변수</title>
</head>
<body>
<%
    request.setAttribute("personObj", new Person("홍길동", 33));
    request.setAttribute("stringObj", "나는 문자열");
    request.setAttribute("integerObj", new Integer(99));
%>

<jsp:forward page="ObjectResult.jsp">
    <jsp:param value="10" name="firstNum"></jsp:param>
    <jsp:param value="20" name="secondNum"></jsp:param>
</jsp:forward>
</body>
</html>
