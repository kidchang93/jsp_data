<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> <!--로그인 확인, 글쓰기는 로그인해야 진입할 수 있으므로-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원제 게시판 (글쓰기 페이지 구현)</title>
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
        function validateForm(form) {  // 폼 내용 검증, 필수항목인 제목과 내용 입력확인
            if (form.title.value == "") {
                alert("제목을 입력하세요.");
                form.title.focus();
                return false; //비어있으면 실패를 뜻하는 false 반환
            }
            if (form.content.value == "") {
                alert("내용을 입력하세요.");
                form.content.focus();
                return false; //비어있으면 실패를 뜻하는 false 반환
            }
        }
        window.onpageshow = function(event) {
            //back 이벤트 일 경우
            if (event.persisted) {
                location.reload(true);
            }
        }
    </script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" />
<h2>회원제 게시판 - 글쓰기(Write)</h2>
<form name="writeFrm" method="post" action="WriteProcess.jsp" <%--form값 전송경로 WriteProcess.jsp--%>
      onsubmit="return validateForm(this);"> <%--submit 이벤트 발생시 validateForm() 호출, false 반환시 폼값 전송하지않음--%>
    <table>
        <tr>
            <td>제목</td>
            <td style= "text-align: left;">
                <input type="text" name="title" style="width: 90%;" />
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td style= "text-align: left;">
                <textarea name="content" style="width: 90%; height: 100px;"></textarea>
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