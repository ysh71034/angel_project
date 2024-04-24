<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="dto" value="${requestScope.dto}"/>
<div id="detail_prod">
    <c:choose>

        <c:when test="${empty dto || empty dto.productName}">
            <div>실패</div>
        </c:when>
        <c:otherwise>
            <div class="info_prod">
                <a href="modprod.do?productNo=${dto.productNo}">수정</a>
                <img src="upload/${dto.dto2.imagepath}" alt="불러오기 실패">

                <ul>
                    <li> ${dto.productNo}</li>
                    <li>판매자 ${dto.userdto.userName}</li>
                    <li>카테고리 ${dto.categoryName}</li>
                    <li>상품명 ${dto.productName}</li>
                    <li>가격 ${dto.price}</li>
                </ul>
                <a href="">채팅하기</a>
                <a href="">채팅방 목록</a>
            </div>
            <div class="info">여기서 제품 설명${dto.description}</div>
            <ul>
                <li>판매자의 다른 상품
                    <p>상품 목록들</p></li>
                <li>같은 카테고리의 추천 상품
                    <p>상품목록들</p></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
