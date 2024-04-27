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
<c:set var="sellerprod" value="${requestScope.sellerprod}"/>
<c:set var="catprod" value="${requestScope.catprod}"/>
<c:set var="roll" value="${requestScope.roll}"/>
<div id="detail_prod">
    <c:choose>

        <c:when test="${empty dto || empty dto.productName}">
            <div>실패</div>
        </c:when>
        <c:otherwise>
            <div class="main">
                <div class="info_prod">
                    <input type="hidden" name="productNo" value="${dto.productNo}">
                    <img src="upload/${dto.dto2.imagepath}" alt="불러오기 실패">
                </div>
                <div class="detail">
                    <ul>
                        <li>카테고리 ${dto.categoryName}</li>
                        <li>
                            <a href="sellerpage.do?sellerNo=${dto.sellerNo}">판매자 ${dto.userdto.userName}</a>
                        </li>

                        <li>상품명 ${dto.productName}</li>
                        <li>가격 ${dto.price}</li>
                    </ul>
                    <c:choose>
                        <c:when test="${roll == 'buyer'}">
                            <a href="enterchat.do?productNo=${dto.productNo}">채팅하기</a>
                        </c:when>
                        <c:otherwise>
                            <a href="chatroom.do?productNo=${dto.productNo}">채팅방 목록</a>
                            <a href="modprod.do?productNo=${dto.productNo}">수정</a>
                            <a href="delete.do?productNo=${dto.productNo}">삭제</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="info"><p>상품 상세 설명</p>${dto.description}</div>
        </c:otherwise>
    </c:choose>
    <ul class="prod_list">
        <li class="seller_prod">
            <span>${dto.userdto.userName}님이 판매중인 다른 상품 </span>
            <ul>
                <c:forEach var="sellerprod" items="${sellerprod}">
                    <li class="seller_border">
                        <a href="detailprod.do?productNo=${sellerprod.productNo}">
                            <img src="upload/${sellerprod.dto2.imagepath}" alt="NO image"></a>
                        <p>${sellerprod.productName}</p>
                    </li>
                </c:forEach>
            </ul>
        </li>

        <li class="cat_prod">
            <span>${dto.categoryName} 카테고리의 추천 상품 </span>
            <ul>
                <c:forEach var="catprod" items="${catprod}">
                    <li class="cat_border">
                        <a href="detailprod.do?productNo=${catprod.productNo}">
                            <img src="upload/${catprod.dto2.imagepath}" alt="No image"></a>
                        <p>${catprod.productName}</p>
                    </li>
                </c:forEach>
            </ul>
        </li>
    </ul>

</div>
</body>
</html>
