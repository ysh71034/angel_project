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
    <script src="js/user/dupID.js"></script>
</head>
<body>
<form method="post" action="join_result.do">
    <div id="join">
        <ul>
            <li>
                <input type="email" name="userID" id="userID" placeholder="이메일" required>
            </li>
            <li>
                <span id="error_msg"></span>
                <button type="button" id="check_btn">중복확인</button>
            </li>
            <li>
                <span class="pwd_type">8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용하세요.</span>
                <input type="password" name="password" id="password" placeholder="비밀번호" required minlength="8">
            </li>
            <li>
                <span class="pwd_mismatch">비밀번호가 일치하지 않습니다.</span>
                <input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호 확인" required>
            </li>
            <li>
                <input type="text" name="userName" id="userName" placeholder="이름" required>
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
                <span class="detail_addr">상세주소를 입력해주세요.</span>
                <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
            </li>
            <li>
                <button type="submit">회원가입</button>
            </li>
        </ul>
    </div>
</form>
<script src="js/user/validCheck.js"></script>
</body>
</html>
