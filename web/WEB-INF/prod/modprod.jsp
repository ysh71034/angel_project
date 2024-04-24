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
<div id="modprod_wrap">
    <form method="post" action="regprod_result.do" enctype="multipart/form-data">
        <ul>
            <li>
                <div class="square-button" id="squareButton">이미지</div>
                <input type="file" name="img" id="fileinput" multiple>
            </li>
            <div class="prodinfo">
                <li class="title">
                    <label for="name">상품명</label>
                    <input type="text" name="name" id="name">
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
            </div>
            <li>
                <button type="submit" class="regbutton">수정하기</button>
            </li>

        </ul>
    </form>
</div>
</body>
</html>
