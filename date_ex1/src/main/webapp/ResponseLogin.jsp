<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-02
  Time: 오후 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>내장 객체 - response</title>
</head>
<body>

<%
    String id = request.getParameter("user_id");
    String pwd = request.getParameter("user_pwd");

    if (id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")) {
        response.sendRedirect("ResponseWelcome.jsp");
    } else {
        // dispatcher = 스프링구조에서 데이터를 주거니 받거니 하는 역할을 해준다
        request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")

                .forward(request, response);
    }
%>
</body>
</html>
