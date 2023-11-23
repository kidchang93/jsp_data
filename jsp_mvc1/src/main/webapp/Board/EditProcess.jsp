<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> <%--로그인 상태 확인 페이지 인클루드--%>
<%
    // 수정할 내용 얻기
    String num = request.getParameter("num");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

// DTO에 각 데이터 저장
    BoardDTO dto = new BoardDTO(); //생성
    dto.setNum(num);
    dto.setTitle(title);
    dto.setContent(content);

// updateEdit 쿼리문으로 DB에 반영
    BoardDAO dao = new BoardDAO(application);
    int affected = dao.updateEdit(dto);
    dao.close();

// 성공/실패 처리
    if (affected == 1) {
        // 성공 시 DTO 객체에 저장된 일련번호 기준으로(수정대상 게시물) 상세 보기 페이지로 이동
        response.sendRedirect("View.jsp?num=" + dto.getNum());
    }
    else {
        // 실패 시 이전 페이지로 이동
        JSFunction.alertBack("수정하기에 실패하였습니다.", out);
    }
%>
