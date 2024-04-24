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
    <link rel="stylesheet" href="css/user/login.css">
</head>

<body>
<form method="post" action="login_result.do">
    <div id="login">
        <ul>
            <li>
                <input type="text" name="userID" id="userID" placeholder="아이디">
            </li>
            <li>
                <input type="password" name="password" id="password" placeholder="비밀번호">
            </li>
            <li>
                <button type="submit">로그인</button>
            </li>
            <a href="join.do">회원가입</a>
        </ul>
    </div>
</form>
</body>
</html>
