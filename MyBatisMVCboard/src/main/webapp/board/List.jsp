<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <title>파일 첨부형 게시판</title>
    <style>
        a {text-decoration:none;}
    </style>
    <script>
        let actionForm = $("#actionForm");

        $("a.page-link").on("click", function(e) {
            e.preventDefault();
            console.log('click');
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });
        const handleSubmit = (e) => {
            console.log("click!")
            actionForm.find("input[name='pageNum']").val(1);
            actionForm.submit();
        }

        $('input[name="searchWord"]').keydown(function(event) {
            if (event.keyCode === 13) {
                event.preventDefault();
                actionForm.find("input[name='pageNum']").val(1);
                actionForm.submit();
            }
        });
    </script>
</head>
<body>
<h2>파일 첨부형 게시판 - 목록 보기(List)</h2>

<!-- 검색 폼 -->
<form method="get" id="actionForm" action="${pageContext.request.contextPath}/board/list.do">
    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
    <table border="1" width="90%">
        <tr>
            <td align="center">
                <select name="searchField">
                    <option value="title" ${(not empty map.searchField) and map.searchField == "title" ? "selected" : ""}>제목</option>
                    <option value="content" ${(not empty map.searchField) and map.searchField == "content" ? "selected" : ""}>내용</option>
                </select>
                <input type="text" name="searchWord" value="${not empty map.searchWord ? map.searchWord : ''}">
                <input type="button" value="검색하기" onclick="handleSubmit()">
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
    <c:choose>
        <c:when test="${empty boardLists}">
        <tr>
            <td colspan="6" align="center">등록된 게시물이 없습니다^^*</td>
        </tr>
        </c:when>
            <c:otherwise>
                <h5>등록된 게시물 개수 : ${requestScope.pageMaker.totalCount}</h5>
                    <c:set var="no" value="${pageMaker.totalCount - ((pageMaker.cri.pageNum - 1) * 10)}" />
                        <c:forEach items="${boardLists}" var="row" varStatus="loop">
                            <tr align="center">
                                <td>
                                        ${no}
                                </td>
                                <td align="left">
                                    <a href="../board/view.do?idx=${row.idx}">${row.title}</a>
                                </td>
                                <td>${row.name}</td>
                                <td>${row.visitcount}</td>
                                <td>${row.postdate}</td>
                                <td>

                                <%--첨부 파일--%>
                                    <c:if test="${not empty row.ofile}">
                                        <a href="../board/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx=${row.idx}">[Down]</a>
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="no" value="${no - 1}" />
                        </c:forEach>
            </c:otherwise>
    </c:choose>
</table>

<c:if test="${requestScope.pageMaker.totalCount > 0 and not empty boardLists}">
    <br>
    <br>
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <c:if test="${pageMaker.prev}">
                    <li class="page-item"><a class="page-link" href="${pageMaker.startPage - 1}">이전</a></li>
                </c:if>
                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <c:choose>
                        <c:when test="${pageMaker.cri.pageNum == num}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${num}</span>
                            </li>
                        </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="${num}">${num}</a>
                                </li>
                            </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageMaker.next}">
                    <li class="page-item"><a class="page-link" href="${pageMaker.endPage + 1}">다음</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</c:if>

<div>
    <a href="${pageContext.request.contextPath}/board/write.do">[글 작성하기]</a>
</div>
</body>
</html>