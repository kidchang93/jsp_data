<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-01
  Time: 오전 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>홈페이지</title>
</head>
<style>
    .grid-container {
        width: 600px;
        /* 얘는 이제부터 그리드 컨테이너입니다! */
        /* 그럼 자동으로 얘 자식요소들은 그리드 아이템이에요~ */
        display: grid;
        /* 그리드에 행(줄)을 각각 70, 200, 70 크기로 3개 만들어요! */
        grid-template-rows: 70px 200px 70px;
        /* 그리드에 열(칸)을 각각 동일한 비율(fr)로 3개 만들어요! */
        grid-template-columns: repeat(3, 1fr);
        /* 선 두께(사이 간격)를 10 크기로 만들어요! */
        gap: 10px;
    }
    .grid-item {
        /* 글씨는 흰색이고요, */
        color: #FFF;
        /* 배경색은 파란색이에요. */
        background: #0169FF;
        /* 그리드 아이템들은 이제부터 플렉스 박스에요. */
        display: flex;
        /* 플렉스 박스 안에서 콘텐츠를 수평 가운데로 정렬하고, */
        justify-content: center;
        /* 수직 가운데로 정렬해요. */
        align-items: center;
    }
    .grid-item.holy {
        /* 선 번호 처음(1)부터 끝(-1)까지 영역을 차지해요 */
        grid-column: 1 / -1;
    }
    .grid-item.footer {
        /* 선 번호 처음(1)부터 끝(-1)까지 영역을 차지해요 */
        grid-column: 1 / -1;
    }
</style>
<body>
<div class="grid-container">
    <div class="grid-item holy">Holy Grail Layout</div>
    <div class="grid-item nav">Nav</div>