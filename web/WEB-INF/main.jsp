<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>천사몰 - main</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div id="wrap">
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