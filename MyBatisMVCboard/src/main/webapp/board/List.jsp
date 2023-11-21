<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<style>a{text-decoration:none;}</style>
</head>
<body>
    <h2>파일 첨부형 게시판 - 목록 보기(List)</h2>

    <!-- 검색 폼 -->
    <form method="get" action="${pageContext.request.contextPath}/board/list.do">
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField">
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>

            <input type="text" name="searchWord" value="${param.searchWord}"/>
            <input type="submit" value="검색하기"/>
        </td>
    </tr>
    </table>
    </form>

    <!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
            <th width="8%">첨부</th>
        </tr>

        <%--choose when otherwise 는 java로 치면
        if else 문이랑 비슷한 반복문 성질을 가지고 있다.
        MVC2 패턴을 잘 나타내고 분리된 메서드를 이용해 동적으로 처리하기 위함.--%>
<c:choose>    
    <c:when test="${ empty boardPagingLists }">  <!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when>

    <c:otherwise>  <!-- 게시물이 있을 때 -->
        <c:set var="no" value="${ map.totalCount - ((map.pageNum - 1) * 10)}" />
        <c:forEach items="${ boardPagingLists }" var="row" varStatus="loop">
        <tr align="center">
            <td>  <!-- 번호 -->
<%--                ${ map.totalCount - (((map.pageNum-1) - map.pageSize) + loop.index)}--%>
                ${no}
            </td>
            <td align="left">  <!-- 제목(링크) -->
                <a href="../board/view.do?idx=${ row.idx }">${ row.title }</a>
            </td> 
            <td>${ row.name }</td>  <!-- 작성자 -->
            <td>${ row.visitcount }</td>  <!-- 조회수 -->
            <td>${ row.postdate }</td>  <!-- 작성일 -->
            <td>  <!-- 첨부 파일 -->
            <c:if test="${ not empty row.ofile }">
                <a href="../board/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a>
            </c:if>
            </td>
        </tr>
            <c:set var="no" value="${no - 1}" />
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>

    <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%">
        <tr align="center">
            <td>
                ${ map.pagingImg }
            </td>
            <td width="100"><button type="button"
                onclick="location.href='../board/write.do';">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>