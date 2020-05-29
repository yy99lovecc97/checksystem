<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/7
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="3;url=main">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/js/bootstrap.bundle.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/css/bootstrap.css" />
    <title>系统登出</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <p class="bg-info">
        您已经成功地登出系统，欢迎下次光临
    </p>
    <h3 class="bg-info">3秒后自动跳转</h3>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
