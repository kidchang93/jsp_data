<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <title>JSP Ajax</title>
    <link rel="stylesheet" href="./css/bootstrap.css">

    <script type="text/javascript">
        var req = new XMLHttpRequest();
        function searchFunction(){
            req.open("Post","./UserSearchServlet?userName="+ encodeURIComponent(document.getElementById("userName").value) , true);
            req.onreadystatechange = searchProcess;
            req.send(null);

        }
        function searchProcess(){
            var table = document.getElementById("ajaxTable");
            table.innerHTML = "";
            if (req.readyState == 4 && req.status == 200){
                var object = eval('(' +req.responseText + ')');
                var result = object.result;
                for (var i = 0; i < result.length; i++){
                    var row = table.insertRow(0);
                    for (var j = 0; j < result[i].length; j++){
                        var cell = row.insertCell(j);
                        cell.innerHTML = result[i][j].value;
                    }

                }
            }
        }
        window.onload = function(){
            searchFunction();
        }
/*        var req = new XMLHttpRequest();

        function searchFunction() {
            req.open("POST", "./UserSearchServlet?userName=" + encodeURIComponent(document.getElementById("userName").value), true);
            req.onreadystatechange = searchProcess;
            req.send(null);
        }

        function searchProcess() {
            var table = document.getElementById("ajaxTable");
            table.innerHTML = "";
            if (req.readyState == 4 && req.status == 200) {
                var object = JSON.parse(req.responseText);
                var result = object.result;
                for (var i = 0; i < result.length; i++) {
                    var row = table.insertRow(0);
                    for (var j = 0; j < result[i].length; j++) {
                        var cell = row.insertCell(j);
                        cell.innerHTML = result[i][j].value;
                    }
                }
            }
        }

        window.onload = function () {
            searchFunction();
        };*/
    </script>
</head>
<body>

<br>
<div class="container">
    <div class="form-group row pull-right">
        <div class="col-xs-8">
            <input class="form-control" id="userName" onkeyup="searchFunction()" type="text" size="20">
        </div>
        <div class="col-xs-2">
            <button class="btn btn-primary" onclick="searchFunction();" type="button">검색</button>
        </div>
    </div>
    <table class="table" style="text-align: center; border: 1px solid #dddddd">
        <thead>
        <tr>
            <th style="background-color: #fafafa; text-align: center;">이름</th>
            <th style="background-color: #fafafa; text-align: center;">나이</th>
            <th style="background-color: #fafafa; text-align: center;">성별</th>
            <th style="background-color: #fafafa; text-align: center;">이메일</th>
        </tr>
        </thead>
        <tbody id="ajaxTable">
        <tr>
            <td>이창규</td>
            <td>31</td>
            <td>남자</td>
            <td>lck930721@naver.com</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>