<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-01
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>page 지시어 - import 속성</title>
    <meta charset ="UTF-8">
</head>
<body>
<%
    Date today = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = dateFormat.format(today);
    out.println("오늘 날짜 : "+todayStr);
%>
</body>
</html>

