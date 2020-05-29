<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/23
  Time: 7:47
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
    <title>提出异动申请</title>
    <s:head></s:head>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">
                <s:property value="#session.user"/>，请填写异动申请
            </h3>
        </div>
        <form action="processApp" method="post" class="form-horizontal">
            <input type="hidden" name="appId" value="${param.appid}">
            <S:if test="fieldErrors.size()>0">
                <div class="form-group">
                    <div class="col-sm-12 text-danger text-center">
                        <s:fielderror></s:fielderror>
                    </div>
                </div>
            </S:if>
            <div class="form-group">
                <label for="type_id" class="col-sm-3">申请类别</label>
                <div class="col-sm-9">
                    <select class="form-control" id="type_id" name="typeId" placeholder="用户名">
                        <s:iterator value="types" var="ty">
                            <option value="${ty.id}">${ty.name}</option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="reason" class="col-sm-3">申请理由</label>
                <div class="col-sm-9">
                    <textarea class="form-control" rows="4" cols="20" name="reason" id="reason"
                              placeholder="填写申请理由"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-9 offset-sm-3">
                    <button type="submit" class="btn">提交申请</button>
                    <button type="reset" class="btn-danger">重填</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
