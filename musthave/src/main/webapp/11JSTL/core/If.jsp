
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL - if</title>
</head>
<body>
<%--변수 선언--%>
<c:set var="number" value="100" />
<c:set var="string" value="JSP" />

<h4>JSTL의 if 태그로 짝수 / 홀수 판단하기</h4>
<c:if test="${ number mod 2 eq 0 }" var="result">
    ${ number }는 짝수 입니다. <br>
</c:if>
result : ${ result } <br>

<h4>문자열 비교와 else 구문 흉내내기</h4>
<c:if test="${string eq 'JAVA' }" var="result2">문자열은 JAVA입니다.
    <br>
</c:if>
<c:if test="${ not result2 }">
    'JAVA'가 아닙니다. <br></c:if>

<h4>조건식 주의사항</h4>
<c:if test="100" var="result3"></c:if>


</body>
</html>
