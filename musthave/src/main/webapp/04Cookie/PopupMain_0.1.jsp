<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<%
    String popupMode = "on";

    Cookie[] cookies = request.getCookies(); // 쿠키를 읽어 popupMode 값 설정
    if(cookies != null) {
        for ( Cookie c : cookies ) {
            String cookieName = c.getName();
            String cookieValue = c.getValue();
            if (cookieName.equals("PopupClose")) {   // "PopupClose" 쿠키가 존재하면
              popupMode = cookieValue;               // popupMode 의 값 갱신
            }
        }
    }

%>

<html>
<head>
    <meta charset = "utf-8">
    <title>쿠키를 이용한 팝업 관리 ver 0.1</title>
    <style>
        div#popup {
            position: absolute;
            top: 100px;
            left: 100px;
            color: yellow;
            width: 270px;
            height: 100px;
            background-color: grey;
        }
        div#popup>div{
            position: relative;
            background-color: #ffffff;
            /*top: 10px;*/
            /*bottom: 0px;*/
            border: 1px solid grey;
            padding:5px 0px 0px 0px;
            color: black;
        }
        .algtxtcenter {
            text-align: center;
        }
        .algcenter {
            text-align: right;
        }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script>
        $(function () {
           $('#closeeBtn').click(function () { // 닫기 버튼(id = "closeBtn") 을 누르면
               $('#popup').hide(); // 팝업창(id = "popup") 을 숨김 처리합니다.
               var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
               if (chkVal == 1) {
                   $.ajax({                             // jQuery의 ajax()함수로 비동기 요청
                       url : './PopupCookie.jsp',       // PopupCookie.jsp 파일에
                       type : 'get',                    // HTTP Get 방식으로
                       data : {inactiveToday : chkVal}, // inactiveToday =<chkVal 변수의 값> 데이터를
                       dataType : "text",               // 응답 데이터의 타입은 일반 텍스트이며
                       success : function(resData) {    // 요청 성공시
                           if (resData != '')           // 응답 데이터가 있다면
                               location.reload();       // 페이지를 새로고칩니다.
                       }
                   })
               }

           });
        });
    </script>
</head>
<body>
<h2>팝업 메인 페이지 (ver 0.1)</h2>
<%
    for (int i = 1 ; i <= 10 ; i++) {
      out.print("현재 팝업창은 "+ popupMode +"상태입니다.<br/>");
    }

    if (popupMode.equals("on")){
%>
<div id = "popup">
    <h2 class = "algtxtcenter">공지사항 팝업입니다.</h2>
    <div class = "algcenter">
        <form name="popFrm">
        <input type="checkbox" id="inactiveToday" value="1"/>
        하루동안 열지 않음
        <input type="button" value="닫기" id="closeeBtn" />
    </form>
    </div>
</div>
<%
    }
%>

</body>
</html>
