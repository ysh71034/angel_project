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
            <span>[디지털] EPSON)프로젝터 (EB-2065)</span>
        </section>
    </div>
    <div class="chat_body">
        <section id="msg_box">
            <p>p.id_01 채팅방</p>
        </section>
        <form>
            <input type="text" name="content">
            <button type="button">전송</button>
        </form>
    </div>
</div>
</body>
</html>
