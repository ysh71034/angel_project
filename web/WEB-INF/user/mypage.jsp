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
    <script defer src="js/user/mypage.js"></script>
</head>
<body>
<section class="headbox">
    <a href="moduser.do">
        <img src="img/logo2.jpg" alt="profile_icon_button" id="headImg">
    </a>
<%--    <h3>홍길동 님 안녕하세요😍</h3>--%>
    <input type="text" name="uno" value="${requestScope.uno}" hidden>
</section>
<section class="statistics">
    <span id="sellCount">판매횟수</span>
<%--    <span class="statistic_content">17회</span>--%>
    <span id="hotCtg">주요 판매 상품</span>
<%--    <span class="statistic_content">가구/장비</span>--%>
</section>
<img class="bluebear" src="img/default.jpg" alt="default_img">
<div class="mysections">
    <section class="sectionlist chatlist">
        <h4>구매 대화중인 채팅방</h4>
        <div id="chatlist">
            <ul class="roomNo">
                <li>방번호</li>
            </ul>
            <ul class="roomName">
                <li>채팅방</li>
            </ul>
        </div>
    </section>
    <section class="sectionlist orderlist">
        <h4>나의 구매 내역</h4>
        <div id="orderlist">
            <ul class="orderNo">
                <li>주문번호</li>
            </ul>
            <ul class="orderprod">
                <li>상품명</li>
            </ul>
            <ul class="orderseller">
                <li>판매자</li>
            </ul>
            <ul class="orderdate">
                <li>거래일</li>
            </ul>
        </div>
    </section>
    <section class="sectionlist selllist">
        <h4>나의 현재 판매 상품</h4>
        <div id="selllist">
            <ul class="sellitem"></ul>
        </div>
    </section>
    <section class="sectionlist pastlist">
        <h4>나의 지난 판매 상품</h4>
        <div id="pastlist">
            <ul class="pastitem"></ul>
        </div>
    </section>
</div>
</body>
</html>
