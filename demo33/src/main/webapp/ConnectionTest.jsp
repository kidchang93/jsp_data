<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-07
  Time: 오후 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="common.JDBConnect"%>
<%@ page import="common.DBConnPool" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
<h2>JDBC 테스트 1</h2>
<%
    JDBConnect jdbc1 = new JDBConnect();
    jdbc1.close();
%>
<h2>컨넥션 풀 테스트</h2>
<%
    DBConnPool pool = new DBConnPool();
    pool.close();
%>
</body>
</html>