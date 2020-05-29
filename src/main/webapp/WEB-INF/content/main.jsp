<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/25
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/js/bootstrap.bundle.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/css/bootstrap.css" />
    <title>Java EE简单工作流系统</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <%--超大屏幕--%>
    <div class="jumbotron">
        <h1>简单工作流系统</h1>
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/login.html" role="button">登录系统</a>
        </p>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
