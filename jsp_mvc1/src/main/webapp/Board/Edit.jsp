<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> <%--로그인 상태인지 확인하기 위해 IsLoggedIn.jsp 인클루드--%>
<%
  String num = request.getParameter("num");  // 일련번호 받기
  BoardDAO dao = new BoardDAO(application);  // DAO 생성
  BoardDTO dto = dao.selectView(num);        // 게시물 가져오기
  String sessionId = session.getAttribute("UserId").toString(); // 로그인 ID 얻기
  if (!sessionId.equals(dto.getId())) {      // 본인인지 확인
    JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
    return;
  }
  dao.close();  // DB 연결 해제
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원제 게시판</title>
    <style>
      table{
        border: 1px solid;
        width: 90%;
      }
      td{
        text-align: center;
      }
      tr, td, th{
        border: 1px solid;
      }
    </style>
    <script type="text/javascript">
      function validateForm(form) {  // 폼 내용 검증
        if (form.title.value == "") {
          alert("제목을 입력하세요.");
          form.title.focus();
          return false;
        }
        if (form.content.value == "") {
          alert("내용을 입력하세요.");
          form.content.focus();
          return false;
        }
      }
    </script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" />
<h2>회원제 게시판 - 수정하기(Edit)</h2>
<%--폼에서 전송하면 수정 처리는 EditProcess에서 진행--%>
<form name="writeFrm" method="post" action="EditProcess.jsp"
      onsubmit="return validateForm(this);">
<%-- 폼에 사용자에게 보이지않게 hidden속성으로, 선택된 게시물의 일련번호를 EditProcess에 그대로 전달--%>
  <input type="hidden" name="num" value="<%= dto.getNum() %>" />
  <table>
    <tr>
      <td>제목</td>
      <td>
        <input type="text" name="title" style="width: 90%;"
               value="<%= dto.getTitle() %>"/> <%--기존 게시물의 제목 미리 입력폼에 채워넣기--%>
      </td>
    </tr>
    <tr>
      <td>내용</td>
      <td>
        <textarea name="content" style="width: 90%; height: 100px;"><%= dto.getContent() %></textarea> <%--기존 게시물의 내용 textarea 태그사이에 채워넣기. 공백 없어야됨--%>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <button type="submit">작성 완료</button>
        <button type="reset">다시 입력</button>
        <button type="button" onclick="location.href='List.jsp';">
          목록 보기</button>
      </td>
    </tr>
  </table>
</form>
</body>
</html>