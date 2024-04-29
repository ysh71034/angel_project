<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-23
  Time: 오후 5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/main_category.css">
    <link rel="stylesheet" href="css/main_newProd.css">
    <script src="js/main/brandnew.js"></script>
</head>
<body>
<div id="category">
    <label>카테고리별 상품</label>
    <ul>
        <li>
            <a href="category_detail.do?catNo=1"><img src="img/bear01.jpeg" alt="도서">도서</a>
        </li>
        <li>
            <a href="category_detail.do?catNo=2"><img src="img/cat01.jpeg" alt="가구/장비">가구/장비</a>
        </li>
        <li>
            <a href="category_detail.do?catNo=3"><img src="img/dog01.jpeg" alt="학습 준비물">학습 준비물</a>
        </li>
        <li>
            <a href="category_detail.do?catNo=4"><img src="img/rabbit01.jpg" alt="행사 용품">행사 용품</a>
        </li>
        <li>
            <a href="category_detail.do?catNo=5"><img src="img/etc.jpg" alt="기타">기타</a>
        </li>
    </ul>
</div>

<div id="brandNew">
    <label>최신 등록 상품</label>
</div>

</body>
</html>
