<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-26
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="dto" value="${requestScope.dto}"/>
<c:set var="sellerprod" value="${requestScope.sellerprod}"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/user/sellerpage.css">
    <script defer src="js/user/sellerpage.js"></script>
</head>
<body>
<div id="sellerInfo">
<section class="headbox">
    <img src="img/logo.jpg" alt="profile_icon_button">
    <h3>판매자 ${dto.userdto.userName} 님의 정보입니다. </h3>
</section>
<section class="statistics">
    <span>안전거래 </span>
    <span class="statistic_content">${dto.userdto.sellCount} 회</span>
<%--    <span class="statistic_content">17회</span>--%>
    <span>주요 판매 상품</span>
    <span class="statistic_content">${dto.categoryName}</span>
</section>
<img  class="bluebear" src="img/default.jpg" alt="default_img">
<section class="sectionlist selllist">
    <h4>판매 상품 목록</h4>
    <div id="selllist">
    <ul>
        <c:forEach var="sellerprod" items="${sellerprod}">
            <li class="seller_border">
                <a href="detailprod.do?productNo=${sellerprod.productNo}">
                    <img src="upload/${sellerprod.dto2.imagepath}" alt="NO image"></a>
                <p>${sellerprod.productName}</p>
            </li>
        </c:forEach>
    </ul>
    </div>
</section>
</div>
</body>
</html>
