<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/7
  Time: 13:21
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
    <title>增加新员工</title>
    <s:head></s:head>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">请您输入新员工的资料</h3>
        </div>
        <form action="processAdd" method="post">
            <s:if test="fieldErrors.size()>0">
                <div class="form-group">
                    <div class="col-sm-12 text-center text-danger">
                        <s:fielderror></s:fielderror>
                    </div>
                </div>
            </s:if>
            <s:token></s:token>
            <div class="form-group">
                <label for="emp_name" class="col-sm-3">员工用户名</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="emp_name"
                           name="employee.name" placeholder="员工用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="emp_pass" class="col-sm-3">员工密码</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="emp_pass"
                           name="employee.pass" placeholder="员工密码">
                </div>
            </div>
            <div class="form-group">
                <label for="emp_salary" class="col-sm-3">员工月薪</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="emp_salary"
                           name="employee.salary" placeholder="员工月薪">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn">添加新员工</button>
                    <button type="reset" class="btn btn-danger">重新输入</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
