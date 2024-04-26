<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-23
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/category/category.css">
</head>
<body>

<div id="tab">
    <ul>
        <li><a href="category_detail.do?catNo=1">도서</a></li>
        <li><a href="category_detail.do?catNo=2">가구/장비</a></li>
        <li><a href="category_detail.do?catNo=3">학습준비물</a></li>
        <li><a href="category_detail.do?catNo=4">행사용품</a></li>
        <li><a href="category_detail.do?catNo=5">기타</a></li>
    </ul>
</div>

<c:set var="list" value="${requestScope.list}"/>
<div id="prodList">
    <label>상품 목록</label>
    <ul id="outerUl">
        <c:choose>
            <c:when test="${empty list || fn:length(list)==0}">
                <li>자료가 없습니다.</li>
            </c:when>
            <c:otherwise>
                <c:forEach var="item" items="${list}">
                    <li>
                        <ul id="innerUl">
                            <a href="detailprod.do?productNo=${item.productNo}">
                            <li><img src="upload/${item.dto2.imagepath}" alt="파일이미지"></li>
                            <li>상품명: ${item.productName}</li>
                            <li>가격: ${item.price}원</li>
                            </a>
                        </ul>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

</body>
</html>
