<%@ taglib prefix="s" uri="/struts-tags" %>
<%--UTF-8
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/18
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/js/bootstrap.bundle.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/css/bootstrap.css" />
    <title>简单工作流系统</title>
    <s:head></s:head>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-text">请输入用户名和密码来登录</h3>
        </div>
        <form action="processLogin" method="post" class="form-horizontal">
            <s:if test="actionMessages.size()>0">
                <div class="row">
                    <div class="col-sm-12 text-info text-center">
                        <s:actionmessage></s:actionmessage>
                    </div>
                </div>
            </s:if>
            <s:if test="actionErrors.size()>0">
                <div class="row">
                    <div class="col-sm-12 text-danger text-center">
                        <s:actionerror></s:actionerror>
                    </div>
                </div>
            </s:if>
            <s:if test="fieldErrors.size()>0">
                <div class="form-group">
                    <div class="col-sm-12 text-danger text-center">
                        <s:fielderror></s:fielderror>
                    </div>
                </div>
            </s:if>
            <div class="form-group">
                <label for="manager_name" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-9">
                    <!--autocomplete="off"关闭回显数据-->
                    <input type="text" class="form-control" id="manager_name" name="manager.name" placeholder="用户名" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label for="manager_pass" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" id="manager_pass" name="manager.pass" placeholder="密码" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label for="vercode" class="col-sm-3 control-label">验证码</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="vercode" name="vercode" placeholder="验证码" autocomplete="off">
                </div>
                <div class="col-sm-3">
                    <img name="d" src="authImg" title="看不清可单击刷新图片" onclick="this.src=this.src+'?'+Math.random()">
                </div>
            </div>
            <div class="form-group card-footer">
                <div class="col-sm-9 offset-3">
                    <button type="submit" class="btn">提交</button>
                    <button type="reset" class="btn btn-danger">重填</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
