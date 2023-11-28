<%--
  Created by IntelliJ IDEA.
  User: Quzz
  Date: 2023-11-29
  Time: 오전 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main View</title>
    <style>
        /* 모달 스타일 */
        #myModal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.7);
        }

        /* 모달 콘텐츠 스타일 */
        .modal-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fefefe;
            padding: 20px;
            text-align: center;
        }
    </style>
    <script>
        function openViewPage() {
            // 모달 열기
            document.getElementById("myModal").style.display = "block";

            // 페이지를 동적으로 로드
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // 로드된 내용을 모달 콘텐츠에 삽입
                    document.getElementById("modalContent").innerHTML = xhr.responseText;
                }
            };
            xhr.open("GET", "view.jsp", true);
            xhr.send();
        }

        function closeViewPage() {
            // 모달 닫기
            document.getElementById("myModal").style.display = "none";
        }

        // 모달 외부를 클릭하면 모달이 닫히도록 이벤트 처리
        window.onclick = function (event) {
            var modal = document.getElementById("myModal");
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };
    </script>
</head>
<body>

<h1>Main View</h1>

<!-- 버튼 클릭 시 openViewPage 함수 호출 -->
<button onclick="openViewPage()">Open View</button>

<!-- 모달 -->
<div id="myModal">
    <div class="modal-content" id="modalContent">
        <!-- 모달에 표시될 콘텐츠 영역 -->
    </div>
</div>

</body>
</html>