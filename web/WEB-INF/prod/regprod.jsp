<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-22
  Time: 오후 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/prod/regprod.css">
</head>
<body>
<div>등록하기</div>
<form method="post" action="regprod_result.do" enctype="multipart/form-data">
<ul>
    <li>
        <label for="name">상품명</label>
        <input type="text" name="name" id="name">
    </li>
    <li>
        <label for="img1">이미지</label>
        <input type="file" name="img1" id="img1">
        <label for="img2">이미지</label>
        <input type="file" name="img2" id="img2">
        <label for="img3">이미지</label>
        <input type="file" name="img3" id="img3">
    </li>
    <li>
        <label for="category">카테고리</label>
        <select name="category" id="category">
            <option value="1">도서</option>
            <option value="2">가구/장비</option>
            <option value="3">학습 준비물</option>
            <option value="4">행사 용품</option>
            <option value="5">기타</option>
        </select>
    </li>
    <li>
        <label for="price">가격</label>
        <input type="text" name="price" id="price">
    </li>
    <li>
        <label for="info">제품설명</label>
        <textarea name="info" id="info" rows="10" cols="30"></textarea>
    </li>
    <li>
        <button type="submit">판매등록</button>
    </li>
</ul>
</form>
</body>
</html>
