<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-26
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/user/sellerpage.css">
    <script defer src="js/user/sellerpage.js"></script>
</head>
<body>
<section class="headbox">
    <img src="img/logo.jpg" alt="profile_icon_button">
    <h3>판매자 홍길동 님의 정보입니다.</h3>
</section>
<section class="statistics">
    <span>안전거래</span>
    <span class="statistic_content">17회</span>
    <span>주요 판매 상품</span>
    <span class="statistic_content">가구/장비</span>
</section>
<img  class="bluebear" src="img/default.jpg" alt="default_img">
<section class="sectionlist selllist">
    <h4>판매 상품 목록</h4>
    <div id="selllist"></div>
</section>
</body>
</html>
