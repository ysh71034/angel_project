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
    <script deter src="js/chat/chatroom.js"></script>
</head>
<body>
<c:set var="sessionID" value="${sessionScope.sessionID}"/>
<c:set var="roll" value="${requestScope.roll}"/>
<div id="chatwrap">
    <div class="top_bar">
        <section class="chat_interface">
            <c:if test="${roll == 'seller'}">
                <button id="contract" type="button">거래확정</button>
<%--                <a href="contract.do?productNo=${param.productNo}&buyerNo=${param.buyerNo}">거래확정</a>--%>
            </c:if>
            <a href="main.do">나가기</a>
        </section>
        <section class="detail_prod">
            <img src="upload/${requestScope.prodImg}" alt="product_img">
            <span>${requestScope.prodCtg}</span>
            <a href="detailprod.do?productNo=${param.productNo}">${requestScope.prodName}</a>
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
            <input type="text" name="roomNo" value="${requestScope.roomNo}" hidden/>
            <input type="text" name="productNo" value="${param.productNo}" hidden/>
            <input type="text" name="buyerNo" value="${requestScope.buyerNo}" hidden/>
            <input type="text" name="sessionID" value="${sessionID}" hidden/>
            <input type="text" name="content" autofocus required>
            <button type="button" id="chatBtn">전송</button>
        </form>
    </div>
</div>
</body>
</html>
