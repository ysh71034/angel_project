<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/chat/chatroom.css">
    <script deter src="js/chatroom.js"></script>
    <script>
        init_data(${param.buyerNo},${param.productNo});
    </script>
</head>
<body>
<c:set var="sessionID" value="${sessionScope.sessionID}"/>
<div id="chatwrap">
    <div class="top_bar">
        <section class="chat_interface">
            <a href="contract.do">거래확정</a>
            <a href="main.do">나가기</a>
        </section>
        <section class="detail_prod">
            <img src="" alt="product_img">
            <span>가구/장비</span>
            <a href="detailprod.do?pid=">[디지털] EPSON)프로젝터 (EB-2065)</a>
        </section>
    </div>
    <div class="chat_body">
        <section id="msg_box">
<%--            <p class="enter">${sessionID} 님이 채팅방에 입장하셨습니다.</p>--%>
<%--            <p class="buyer_name">😊 ${sessionID}</p>--%>
<%--            <p class="buyer_msg">안녕하세요! 혹시 제주도에도 배송이 되는지 여쭤보려구요~~~</p>--%>
<%--            <p class="seller_name">😊 판매자</p>--%>
<%--            <p class="seller_msg">${param.buyerNo}</p>--%>
        </section>
        <form>
            <input type="text" name="content" autofocus>
            <button type="button">전송</button>
        </form>
    </div>
</div>
</body>
</html>
