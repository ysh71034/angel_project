<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-23
  Time: 오후 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/prod/detailprod.css">
</head>
<body>
<div id="detail_prod">
    <img src="upload/${img}" alt="불러오기 실패">
    <div class="info_prod">
    <ul>
        <li>카테고리</li>
        <li>상품명</li>
        <li>가격</li>
    </ul>
    <a href="">채팅하기</a>
    </div>
    <div class="info">여기서 제품 설명</div>
    <ul>
        <li>판매자의 다른 상품
            <p>상품 목록들</p></li>
        <li>같은 카테고리의 추천 상품
            <p>상품목록들</p></li>
    </ul>
</div>
</body>
</html>
