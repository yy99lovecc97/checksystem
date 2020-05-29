<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/27
  Time: 7:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/js/bootstrap.bundle.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-4.3.1-dist/css/bootstrap.css" />
    <title>查看本部门全部员工</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">
                查看本部门全部员工
            </h3>
        </div>
        <div class="card-body">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>员工</th>
                    <th>密码</th>
                    <th>工资</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="emps">
                    <tr>
                        <td><s:property value="empName"/></td>
                        <td><s:property value="empPass"/></td>
                        <td><s:property value="amount"/></td>
                        <td><a class="bg-danger text-white" href="delEmp?id=<s:property value="id"/>">删除</a></td>
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
