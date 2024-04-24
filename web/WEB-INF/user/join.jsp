<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="js/user/findAddress.js"></script>
    <c:set var="checkID" value="${requestScope.checkID}"/>
    <script src="js/user/validation.js"></script>
</head>
<body>
<form method="post" action="join_result.do">
    <div id="join">
        <ul>
            <c:out value="아이디확인: ${checkID}"/>
            <li>
                <input type="text" name="userID" id="userID" placeholder="이메일">
                <button type="button" onclick="validateCheck()">중복확인</button>
            </li>
            <li>
                <input type="password" name="password" id="password" placeholder="비밀번호">
            </li>
            <li>
                <input type="password" name="checkpwd" id="checkpwd" placeholder="비밀번호 확인">
            </li>
            <li>
                <input type="text" name="userName" id="userName" placeholder="이름">
            </li>
            <li>
                <input type="text" name="postcode" id="postcode" placeholder="우편번호" onclick="findAddr()" readonly>
            </li>
            <li>
                <input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소" readonly>
            </li>
            <li>
                <input type="text" name="jibunAddress" id="jibunAddress" placeholder="지번주소" readonly>
            </li>
            <li>
                <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목" readonly>
            </li>
            <li>
                <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
            </li>
            <li>
                <div id="result"></div>
                <button type="submit">회원가입</button>
            </li>
        </ul>
    </div>
</form>
</body>
</html>
