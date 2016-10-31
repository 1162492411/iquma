<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'updateUserp.jsp' starting page</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 这里是更新账户信息页面 -->
<form action="${pageContext.request.contextPath}/admin/users" method="post" >
    <input type="hidden" name="_method" value="put" />
    <div class="panel panel-default">
        <div class="panel-heading">更新账户</div>
        <div class="panel-body" id="profileBody">
            <!-- 用户id -->
            <div class="form-group" id="idPanel">
                <label>用户id</label>
                <input type="text" class="form-control" name="id" value="${user.id}" placeholder="用户id"
                       readonly="readonly"/>
            </div>
            <%--用户昵称 --%>
            <div class="form-group" id="namePanel">
                <label>用户昵称</label>
                <input type="text" class="form-control" name="name" value="${user.name}" placeholder="真实姓名"/>
            </div>
            <%--用户角色id --%>
            <div class="form-group" id="ridPanel">
                <label>角色id</label>
                <input type="text" class="form-control" name="rid" value="${user.rid}" placeholder="角色id"/>
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" value="${user.email}" placeholder="安全邮箱"/>
            </div>
            <%-- 用户状态 --%>
            <div class="form-group" id="isBlockPanel">
                <label>用户状态</label>
                <input type="text" class="form-control" name="isBlock" value="${user.isBlock}" placeholder="用户状态"
                       readonly="readonly"/>
            </div>

            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" value="更新"/>
                <input type="reset" class="btn btn-default" value="重设"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
