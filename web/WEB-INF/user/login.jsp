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
<ul>
    <li>
        <label for="userID">이메일</label>
        <input type="text" name="userID" id="userID">
    </li>
    <li>
        <label for="password">비밀번호</label>
        <input type="password" name="password" id="password">
    </li>
    <li>
        <input type="submit" value="로그인">
    </li>
</ul>
    <a href="join.do">회원가입</a>
</form>
</body>
</html>
