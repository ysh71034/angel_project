<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-26
  Time: 오전 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/user/join.css">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="js/user/findAddress.js"></script>
    <script defer src="js/user/deleteUser.js"></script>
</head>
<body>
<c:set var="uno" value="${requestScope.uno}"/>
<c:set var="dto" value="${requestScope.dto}"/>
<form method="post" action=moduser_result.do?uno=${uno}">
    <div id="join">
        <ul>
            <li>
                <span class="pwd_type">8글자 이상, 영문, 숫자, 특수문자 @$!%*#?&를 사용하세요.</span>
                <input type="password" name="password" id="password" placeholder="비밀번호" required minlength="8" required>
            </li>
            <li>
                <span class="pwd_mismatch">비밀번호가 일치하지 않습니다.</span>
                <input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호 확인">
            </li>
            <li>
                <input type="text" name="userName" id="userName" placeholder="이름" value="${dto.userName}">
            </li>
            <li>
                <input type="text" name="postcode" id="postcode" placeholder="우편번호" onclick="findAddr()">
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
                <span class="detail_addr">상세주소를 입력해주세요.</span>
                <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${dto.address}">
            </li>
            <li>
                <button type="submit">정보수정</button>
            </li>
            <li>
                <input type="text" name="uno" value="${uno}" hidden>
                <button type="button" id="delete_btn">회원탈퇴</button>
            </li>
        </ul>
    </div>
</form>
<script src="js/user/validCheck.js"></script>
</body>
</html>
