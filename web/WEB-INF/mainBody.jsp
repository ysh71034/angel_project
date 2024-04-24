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
</head>
<body>

<div id="category">
    <label>카테고리별 상품</label>
    <ul>
        <li>
            <a href="category_detail.do"><img src="img/bluebear.jpg" alt="도서">도서</a>
        </li>
        <li>
            <a href="category_detail.do"><img src="img/bluebear.jpg" alt="가구/장비">가구/장비</a>
        </li>
        <li>
            <a href="category_detail.do"><img src="img/bluebear.jpg" alt="학습 준비물">학습 준비물</a>
        </li>
        <li>
            <a href="category_detail.do"><img src="img/bluebear.jpg" alt="행사 용품">행사 용품</a>
        </li>
        <li>
            <a href="category_detail.do"><img src="img/bluebear.jpg" alt="기타">기타</a>
        </li>
    </ul>
</div>

<div id="brandNew">
    <label>최신 등록 상품</label>
    <ul>
        <li>
            <a href="#구매상세"><img src="img/logo.jpg" alt="도서"></a>
        </li>
        <li>
            <a href="#"><img src="img/logo.jpg" alt="가구/장비"></a>
        </li>
        <li>
            <a href="#"><img src="img/logo.jpg" alt="학습 준비물"></a>
        </li>
        <li>
            <a href="#"><img src="img/logo.jpg" alt="행사 용품"></a>
        </li>
        <li>
            <a href="#"><img src="img/logo.jpg" alt="기타"></a>
        </li>
    </ul>
</div>

</body>
</html>
