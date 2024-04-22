<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="wrap">
    <haeder>
        <jsp:include page="header.jsp"/>
    </haeder>
    <section>
        <jsp:include page="${param.page}"/>
    </section>
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
</html>
