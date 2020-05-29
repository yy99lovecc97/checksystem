
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/25
  Time: 11:26
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
    <title>电子打卡</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">电子打卡系统</h3>
        </div>
        <div class="card-body">
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <div class="row">
            <div class="col-sm-2 offset-sm-5">
                <s:property value="#session.user"/> ，
            </div>
        </div>
        <div class="row">
            <!--offset-4 报错了io流错误-->
            <div class="col-sm-2 offset-sm-4">
                <!-- 当punchIsValid为1、3时，可上班打卡 -->
                <s:if test="punchIsValid==1 || punchIsValid==3">
                    <a href="${pageContext.request.contextPath}/employeeCome"
                       class="btn">上班打卡</a>
                </s:if>
            </div>
            <div class="col-sm-2">
                <!-- 当punchIsValid为2、3时，可下班打卡 -->
                <s:if test="punchIsValid==2 || punchIsValid==3">
                    <a href="${pageContext.request.contextPath}/employeeLeave"
                       class="btn">下班打卡</a>
                </s:if>
            </div>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
        </div>
    </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
