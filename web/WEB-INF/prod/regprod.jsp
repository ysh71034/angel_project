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
    <script defer src="js/prod/regprod.js"></script>
</head>
<body>
<div id="reg_wrap">
<form method="post" action="regprod_result.do" enctype="multipart/form-data">
<ul>
    <li>
        <div id="preview"></div>
        <label for="fileinput" class="imagefile" id="fileLabel">파일 선택</label>
        <input type="file" name="img" id="fileinput"  multiple>

    </li>
    <div class="prodinfo">
    <li class="title">
        <label for="productName">상품명</label>
        <input type="text" name="productName" id="productName">
    </li>
    <li>
        <label for="category">카테고리</label>
        <select name="category" id="category">
            <option value="book" selected>도서</option>
            <option value="furniture">가구/장비</option>
            <option value="req">학습 준비물</option>
            <option value="party">행사 용품</option>
            <option value="etc">기타</option>
        </select>
    </li>
    <li>
        <label for="price">가격</label>
        <input type="text" name="price" id="price">
    </li>
    <li>
        <label for="description">제품설명</label>
        <textarea name="description" id="description" rows="10" cols="30"></textarea>
    </li>
    </div>
    <li>
        <button type="submit" class="regbutton">판매등록</button>
    </li>

</ul>
</form>
</div>
</body>
</html>
