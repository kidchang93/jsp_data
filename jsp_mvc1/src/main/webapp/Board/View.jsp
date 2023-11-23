<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String num = request.getParameter("num");  // List.jsp에서 설정한 일련번호 받기. View.jsp를 먼저실행하면 해당 번호가 없기 때문에 500 에러가 발생.

  BoardDAO dao = new BoardDAO(application);  // DAO 생성
  dao.updateVisitCount(num);                 // 조회수 증가
  BoardDTO dto = dao.selectView(num);        // 게시물 데이터 가져오기
  dao.close();                               // DB 연결 해제
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
      text-align: left;
    }
    tr, td, th{
      border: 1px solid;
    }
  </style>
  <script>
    function deletePost() {
      var confirmed = confirm("정말로 삭제하겠습니까?"); //widows.confirm 함수사용. dialog창 팝업
      if (confirmed) {
        var form = document.writeFrm;       // 이름(name)이 "writeFrm"인 폼 선택. 동일페이지 아래에 해당폼 상세보기 있음
        form.method = "post";               // 전송 방식
        form.action = "DeleteProcess.jsp";  // 전송 경로. 폼값을 DeleteProcess에 전송하면 거기서 Delete처리
        form.submit();                      // 폼값 전송. 이때 writeFrm 폼에는 일련번호 num이 히든타입으로 인풋되고 있어 사용자모르게 함께 전송됨.
      }
    }
  </script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" /> <!-- 공통 링크 -->
<h2>회원제 게시판 - 상세 보기(View)</h2>
<%--dto에 저장된 내용 테이블형식으로 출력--%>
<form name="writeFrm">
  <input type="hidden" name="num" value="<%= num %>" />

  <table>
    <tr>
      <td>번호</td>
      <td><%= dto.getNum() %></td>
      <td>작성자</td>
      <td><%= dto.getName() %></td>
    </tr>
    <tr>
      <td>작성일</td>
      <td><%= dto.getPostdate() %></td>
      <td>조회수</td>
      <td><%= dto.getVisitcount() %></td>
    </tr>
    <tr>
      <td>제목</td>
      <td colspan="3"><%= dto.getTitle() %></td>
    </tr>
    <tr>
      <td>내용</td>
      <td colspan="3" height="100">
        <%= dto.getContent().replace("\r\n", "<br/>") %></td> <!-- replace() 메서드로 엔터키를 <br>태그로 변경해 웹브라우저에 줄바꿈적용 -->
    </tr>
    <tr>
      <td colspan="4" style="text-align: center">
        <%
          if (session.getAttribute("UserId") != null // session영역에 속성값이 있는가? 즉, 로그인한 상태인가?
                  && session.getAttribute("UserId").toString().equals(dto.getId())) { // 로그인 아이디와 DTO 객체 아이디가 일치? 작성자 본인인지 확인
          //위 if문 만족시 수정, 삭제버튼 보여줌
        %>
        <button type="button" onclick="location.href='Edit.jsp?num=<%= dto.getNum() %>';">
          수정하기
        </button>
        <%--삭제하기 버튼을 클릭하면 동일 페이지 상단의 deletePost() 함수 실행--%>
        <button type="button" onclick="deletePost();">
          삭제하기
        </button>
        <%
          } // 작성자 본인이 아니어도 목록보기 버튼은 노출
        %>
        <button type="button" onclick="location.href='List.jsp';">
          목록 보기
        </button>
      </td>
    </tr>
  </table>
</form>
</body>
</html>