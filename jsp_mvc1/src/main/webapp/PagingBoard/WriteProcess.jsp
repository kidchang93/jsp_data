<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> <%-- 로그인 상태인지 체크하기 위해 include--%>
<%
    // request에 전송된 폼값 받기
    String title = request.getParameter("title");
    String content = request.getParameter("content");

// 폼값을 DTO 객체에 저장
    BoardDTO dto = new BoardDTO();
    dto.setTitle(title);
    dto.setContent(content);
    dto.setId(session.getAttribute("UserId").toString());
    // session 영역에 저장되어 있는 사용자 아이디까지 DTO에 담은이유는?
    // board 테이블의 id 컬럼은 member 테이블의 id 컬럼과 외래키로 설정되어 있으므로, id가 빈값이면 Insert할때 제약조건 위배로 오류가 발생하기 때문.

// DAO 객체를 통해 DB에 DTO 저장
    BoardDAO dao = new BoardDAO(application);
    int iResult = dao.insertWrite(dto);
    //더미데이터 생성용 코드
//    int iResult = 0;
//    for (int i = 1; i < 100; i++) {
//        dto.setTitle(title + "-" + i);
//        iResult = dao.insertWrite(dto);
//    }
    dao.close();

// 성공 or 실패?
    if (iResult == 1) {
        response.sendRedirect("List.jsp"); //성공시 목록 페이지
    } else {
        JSFunction.alertBack("글쓰기에 실패하였습니다.", out); //실패하면 경고창
    }
%>
