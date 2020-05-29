<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/24
  Time: 16:43
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
    <title>查看自己的非正常出勤</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
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
                    <th>打卡日期</th>
                    <th>异动名称</th>
                    <th>打卡时间</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="unAttend">
                    <tr>
                        <td><s:property value="dutyDay"/></td>
                        <td><s:property value="unType"/></td>
                        <td><s:property value="time"/></td>
                        <td><a href='appChange?attid=<s:property value="id"/>'>申请改变</a></td>
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
