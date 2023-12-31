<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Board.BoardDAO" %>
<%@ page import="Board.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>

<%
    // DAO 생성
    BoardDAO dao = new BoardDAO();

    // 뷰에 전달할 매개변수 저장용 맵 생성
    Map<String, Object> map = new HashMap<String, Object>();

    List<BoardDTO> boardLists = dao.selectList(map);  // 게시물 목록 받기
    request.setAttribute("boardLists", boardLists);
    request.setAttribute("map", map);
%>

<html>
<head>
    <title>회원제 게시판</title>
    <style>
        table {
            width: 100%;
            border: 1px solid #444444;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #444444;
            padding: 10px;
            text-align: center;
            flex: 1;
        }
        td{
            text-align: center;
        }
        .writebtn{
            text-align: right;
        }
        .viewbtn{
            text-align: left;
        }
        .flextable{
            display: flex;
        }
        a {
            text-align: center;
            text-decoration: none; /* 링크의 밑줄 제거 */
        }

    </style>
</head>
<body>
<h2>목록 보기(List)</h2>
<form method="get">
    <table>
        <tr>
            <td>
                <select name="searchField">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="searchWord"/>
                <input type="submit" value="검색하기"/>
            </td>
        </tr>
    </table>
</form>
<div  class="flextable">
    <table style="border: 1px solid; width:100%;">
        <tr>
            <th style="width:10%;">번호</th>
            <th style="width:10%;">제목</th>
            <th style="width:15%;">작성자</th>
            <th style="width:10%;">조회수</th>
            <th style="width:15%;">작성일</th>
        </tr>
        <c:choose>
            <c:when test="${ empty boardLists }">  <!-- 게시물이 없을 때 -->
                <tr>
                    <td colspan="6" align="center">
                        등록된 게시물이 없습니다^^*
                    </td>
                </tr>
            </c:when>
            <c:otherwise>  <!-- 게시물이 있을 때 -->
                <c:forEach items="${ boardLists }" var="row" varStatus="loop">
                    <tr align="center">
                        <td> ${ row.idx } </td>
                        <td> <a href="${pageContext.request.contextPath}/View.jsp?idx=${ row.idx }">${ row.title }</a></td>
                        <td>${ row.name }</td>  <!-- 작성자 -->
                        <td>${ row.visitCount }</td>  <!-- 조회수 -->
                        <td>${ row.postDate }</td>  <!-- 작성일 -->
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
</div>
<table>
    <tr>
        <td class="writebtn"><button type="button" onclick="location.href='Write.jsp';">글쓰기</button> </td>
    </tr>
</table>

</body>
</html>
