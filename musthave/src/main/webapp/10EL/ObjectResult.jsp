<%@ page import="common.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<meta charset="UTF-8">
<head>
    <title>표현 언어(EL) - 객체 매개 변수</title>
</head>
<body>
<h2>영역을 통해 전달된 객체 읽기</h2>
<ul>
    <li>Person 객체 => 이름 : ${personObj.name}, 나이 : ${personObj.age}</li>
    <li>String 객체 => ${requestScope.stringObj}</li>
    <li>Integer 객체 => ${integerObj}</li>
</ul>
<h2>매개 변수로 전달된 값 읽기</h2>
<ul>
    <li>${param.firstNum + param['secondNum']}</li>
    <li>${param.firstNum} + ${param["secondNum"]}</li>
</ul>
</body>
</html>
