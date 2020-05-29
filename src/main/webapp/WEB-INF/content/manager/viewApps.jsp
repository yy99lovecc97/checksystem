<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/27
  Time: 8:02
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
    <title>处理申请</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">
                当前用户：<s:property value="#session.user"/>
            </h3>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>员工名</th>
                    <th>缺勤类型</th>
                    <th>申请类型</th>
                    <th>理由</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="apps">
                    <tr>
                        <td><s:property value="emp"/></td>
                        <td><s:property value="unAttend"/></td>
                        <td><s:property value="toAttend"/></td>
                        <td><s:property value="reason"/></td>
                        <td>
                            <a href='check.action?result=pass&appid=<s:property value="id"/>'>
                                通过</a>&nbsp;&nbsp;
                            <a href='check.action?result=deny&appid=<s:property value="id"/>'>
                                拒绝</a>
                        </td>
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
