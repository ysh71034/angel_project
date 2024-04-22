<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>천사몰 - 채팅방</title>
    <link rel="stylesheet" href="css/chat/chatroom.css">
</head>
<body>
<div id="chatwrap">
    <div class="top_bar">
        <section class="chat_interface">
            <a href="contract">거래확정</a>
            <a href="index">나가기</a>
        </section>
        <section class="detail_prod">
            <img src="" alt="product_img">
            <span>가구/장비</span>
            <a href="detailprod.do?pid=">[디지털] EPSON)프로젝터 (EB-2065)</a>
        </section>
    </div>
    <div class="chat_body">
        <section id="msg_box">
            <p class="enter">p.id_01 채팅방</p>
            <p class="buyer_name">😊 구매자</p>
            <p class="buyer_msg">안녕하세요!</p>
            <p class="seller_name">😊 판매자</p>
            <p class="seller_msg">판매자입니다.</p>
        </section>
        <form>
            <input type="text" name="content" autofocus>
            <button type="button">전송</button>
        </form>
    </div>
</div>
</body>
</html>
