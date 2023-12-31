<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<script type="text/javascript">
    function validateForm(form) {
        if (form.name.value == "") {
            alert("작성자를 입력하세요.");
            form.name.focus();
            return false;
        }
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
<h2>파일 첨부형 게시판 - 수정하기(Edit)</h2>
<%-- 클릭 되었을 때 자바스크립트 함수를 호출한다--%>
<form name="writeFrm" method="post" enctype="multipart/form-data" action="../board/edit.do" onsubmit="return validateForm(this);">
<input type="hidden" name="idx" value="${ vo.idx }"/>
<input type="hidden" name="prevOfile" value="${ vo.ofile }" />
<input type="hidden" name="prevSfile" value="${ vo.sfile }" />
    
<table border="1" width="90%">
    <tr>
        <td>작성자</td>
        <td><!--EL언어를 통해 vo에 저장되어있는 name값을 대입-->
            <input type="text" name="name" style="width:150px;" value="${ vo.name }" />
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td>
            <input type="text" name="title" style="width:90%;" value="${ vo.title }" />
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>
            <textarea name="content" style="width:90%;height:100px;">${ vo.content }</textarea>
        </td>
    </tr>
    <tr>
        <td>첨부 파일</td>
        <td>
            <input type="file" name="ofile" />
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <button type="submit">작성 완료</button>
            <button type="reset">RESET</button>
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/board/list.do';">
                목록 바로가기
            </button>
        </td>
    </tr>
</table>    
</form>
</body>
</html>