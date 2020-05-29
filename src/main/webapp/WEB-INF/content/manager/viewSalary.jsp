<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/8
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/js/bootstrap.bundle.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/css/bootstrap.css" />
    <title>经理查看自己工资</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">
                当前用户：<s:property value="#session.user"></s:property>
            </h3>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>发薪月份</th>
                    <th>薪水</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="salarys">
                    <tr>
                        <td><s:property value="payMonth"/></td>
                        <td><s:property value="amount"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
