<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    //로그인 하지 않았다면 로그인 폼으로 이동
    if (session.getAttribute("UserId") == null) { // session 영역에 UserId라는 속성값이 있는지 확인. 없으면 로그인 안한것.
        JSFunction.alertLocation("로그인 후 이용해주십시오.", //JSFunction의 alertLocation 메소드 사용하여 LoginForm으로 이동
                "../06Session/LoginForm.jsp", out);
        return;
    }
%>