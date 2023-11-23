<%@ page import="membership.MemberDAO" %>
<%@ page import="membership.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<%
    //로그인 폼으로부터 받은 아이디와 패스워드
    String userId = request.getParameter("user_id");
    String userPwd = request.getParameter("user_pw");
    //web.xml에서 가져온데이터 베이스 연결정보
    String mariadbDriver = application.getInitParameter("MariaDB_Driver");
    String mariadbURL = application.getInitParameter("MariaDB_URL");
    String mariadbId = application.getInitParameter("MariaDB_Id");
    String mariadbPwd = application.getInitParameter("MariaDB_Pwd");

    MemberDAO dao = new MemberDAO(mariadbDriver, mariadbURL, mariadbId, mariadbPwd);
    MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
    dao.close();

    if(memberDTO.getId() != null){
      session.setAttribute("UserId", memberDTO.getId());
      session.setAttribute("UserName", memberDTO.getName());
      response.sendRedirect("LoginForm.jsp");
    }else {
      request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
      request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
    }

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
