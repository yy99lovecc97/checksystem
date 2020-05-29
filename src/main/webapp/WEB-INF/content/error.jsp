<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/7
  Time: 14:46
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
    <title>出错提示</title>
</head>
<body>
<div class="container">
    <div class="card bg-danger text-center">
        <div class="card-header">
            <h4 class="card-title">系统处理过程中发生了一个错误，信息如下：</h4>
        </div>
        <p>&nbsp;</p>
        <s:property value="exception.message"></s:property>
        <p>&nbsp;</p>
        <div class="card-footer">
            请您先核对输入，如果再次出现该错误，<br>
            请登录<a href="http://www.crazyit.org">http://www.crazyit.org</a>寻求答案，谢谢。
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
