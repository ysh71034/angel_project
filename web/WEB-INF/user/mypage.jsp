<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-23
  Time: 오전 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/user/mypage.css">
</head>
<body>
<section class="headbox">
    <img src="img/logo.jpg" alt="profile_icon_button">
    <h3>홍길동 님 안녕하세요😍</h3>
</section>
<section class="statistics">
    <span>안전거래</span>
    <span class="statistic_content">17회</span>
    <span>주요 판매 상품</span>
    <span class="statistic_content">가구/장비</span>
</section>
<img class="bluebear" src="img/bluebear.jpg" alt="bluebear_img">
<section class="sectionlist chatlist">
    <h4>대화중인 채팅방</h4>
    <div id="chatlist"></div>
</section>
<section class="sectionlist orderlist">
    <h4>나의 거래 내역</h4>
    <div id="orderlist"></div>
</section>
<section class="sectionlist selllist">
    <h4>나의 판매상품</h4>
    <div id="selllist"></div>
</section>
<section class="sectionlist wishlist">
    <h4>내가 찜한 상품</h4>
    <div id="wishlist"></div>
</section>
</body>
</html>
