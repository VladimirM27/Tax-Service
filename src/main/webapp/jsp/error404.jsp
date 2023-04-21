<%--
  Created by IntelliJ IDEA.
  User: volodymyrm27
  Date: 21.04.2023
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/error404.css">

</head>
<body>

<div class="noise"></div>
<div class="overlay"></div>
<div class="terminal">
    <h1>Error <span class="errorcode">404</span></h1>
    <p class="output">The page you are looking for might have been removed, had its name changed or is temporarily unavailable.</p>
    <p class="output">Please try to  <a href="${pageContext.request.contextPath}/jsp/index.jsp">return to the homepage</a>.</p>
    <p class="output">Good luck.</p>
</div>
</body>
</html>
