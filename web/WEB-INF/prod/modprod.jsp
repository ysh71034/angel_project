<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-23
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/prod/regprod.css">
    <script defer src="js/prod/regprod.js"></script>
</head>
<body>
<c:set var="dto" value="${requestScope.dto}"/>
<div id="modprod_wrap">
    <form method="post" action="modprod_result.do" enctype="multipart/form-data">
        <ul>
            <li>
                <input type="hidden" name="productNo" value="${dto.productNo}">
                <div class="square-button" id="squareButton">이미지</div>
                <input type="file" name="img" id="fileinput" multiple>
                <img src="upload/${dto.dto2.imagepath}" alt="No image">
            </li>
            <div class="prodinfo">
                <li class="title">
                    <label for="productName">상품명</label>
                    <input type="text" name="productName" id="productName" value="${dto.productName}">
                </li>
                <li>
                    <label for="category">카테고리</label>
                    <select name="category" id="category">
                        <option value="book" ${dto.categoryName == "book" ? 'selected' : ''}>도서</option>
                        <option value="furniture" ${dto.categoryName == "furniture" ? 'selected' : ''}>가구/장비</option>
                        <option value="req" ${dto.categoryName == "req" ? 'selected' : ''}>학습 준비물</option>
                        <option value="party" ${dto.categoryName == "party" ? 'selected' : ''}>행사 용품</option>
                        <option value="etc" ${dto.categoryName == "etc" ? 'selected' : ''}>기타</option>
                    </select>
                    <%--<select name="category" id="category">
                        <option value="1">도서</option>
                        <option value="2">가구/장비</option>
                        <option value="3">학습 준비물</option>
                        <option value="4">행사 용품</option>
                        <option value="5">기타</option>
                    </select>--%>
                </li>
                <li>
                    <label for="price">가격</label>
                    <input type="text" name="price" id="price" value="${dto.price}">
                </li>
                <li>
                    <label for="description">제품설명</label>
                    <textarea name="description" id="description" rows="10" cols="30">${dto.description}</textarea>
                </li>
            </div>
            <li>
                <button type="submit" class="regbutton">수정하기</button>
            </li>

        </ul>
    </form>
</div>
</body>
</html>
