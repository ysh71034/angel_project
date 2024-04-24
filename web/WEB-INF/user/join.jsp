<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/user/join.css">
</head>
<body>
<form method="post" action="join_result.do">
    <div id="join">
        <ul>
            <li>
                <input type="text" name="userID" id="userID" placeholder="아이디">
            </li>
            <li>
                <input type="password" name="password" id="password" placeholder="비밀번호">
            </li>
            <li>
                <input type="text" name="userName" id="userName" placeholder="이름">
            </li>
            <li>
                <input type="text" name="address" id="address" placeholder="주소">
                <input type="button" value="우편번호 찾기">
            </li>
            <li>
                <button type="submit">회원가입</button>
            </li>
            <a href="main.do">메인으로</a>
        </ul>
    </div>
</form>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>
