<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>展示所有用户页面</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>

<body>
<%-- 数据表格 --%>
<div class="table-responsive">
    <table class="table table-hover" id="userTable">
        <%-- 设置表头 --%>
        <thead>
        <tr>
            <th>用户id</th>
            <th>角色id</th>
            <th>用户昵称</th>
            <th>用户头像</th>
            <th>用户邮箱</th>
            <th>用户状态</th>
            <th>用户威望</th>
            <th>用户获赞</th>
            <th>操作类型</th>
        </tr>
        </thead>

        <%-- 设置数据 --%>
        <tbody>
        <c:forEach var="user" items="${userMap}">
            <tr id="${user.id}">
                <td>${user.id}</td>
                <td>${user.rid}</td>
                <td>${user.name}</td>
                <td><img src="${user.avatar}" style="width:60px;height:60px"></td>
                <td>${user.email}</td>
                <td>${user.isBlock}</td>
                <td>${user.prestige}</td>
                <td>${user.appCount}</td>
                <td>
                    <input type="button" class="btn btn-primary" id="updateButton" value="更新"
                           onclick="updateUser(${user.id})"/>
                    <input type="button" class="btn btn-primary" id="BlockButton" value="封禁"
                           onclick="blockUser(${uid})"/>
                    <input type="button" class="btn btn-danger" id="deleteButton" value="删除"
                           onclick="deleteUser(${user.id})"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
</body>
</html>
