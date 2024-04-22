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
</head>
<body>
<form method="post" action="join_result.do">
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
            <label for="userName">이름</label>
            <input type="text" name="userName" id="userName">
        </li>
        <li>
            <label for="address">주소</label>
            <input type="text" name="address" id="address">
        </li>
        <li>
            <input type="submit" value="회원가입">
        </li>
    </ul>
    <a href="main.do">메인으로</a>
</form>
</body>
</html>
