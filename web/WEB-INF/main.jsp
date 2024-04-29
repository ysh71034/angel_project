<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poor+Story&family=Stylish&family=Yeon+Sung&display=swap" rel="stylesheet">
</head>
<body>
<div id="wrap" class="poor-story-regular">
    <haeder id="header">
        <jsp:include page="header.jsp"/>
    </haeder>
    <section id="jsp_body">
        <jsp:include page="${param.page}"/>
    </section>
    <footer id="footer">
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
</html>
