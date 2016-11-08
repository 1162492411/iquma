<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<head>

    <title>添加用户</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 添加账户 -->
<form action="${pageContext.request.contextPath}/admin/users/add/validator">

    <div class="panel panel-default">
        <div class="panel-heading">添加账户</div>
        <div class="panel-body" id="profileBody">
            <!-- 用户id -->
            <div class="form-group" id="idPanel">
                <label>用户id</label>
                <input type="text" class="form-control" name="id" placeholder="用户id"/>
            </div>
            <%--用户昵称 --%>
            <div class="form-group" id="namePanel">
                <label>用户昵称</label>
                <input type="text" class="form-control" name="name" placeholder="真实姓名"/>
            </div>
            <%--用户角色id --%>
            <div class="form-group" id="ridPanel">
                <label>角色id</label>
                <input type="text" class="form-control" name="rid" placeholder="角色id"/>
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" placeholder="安全邮箱"/>
            </div>
            <%-- 用户状态 --%>
            <input type="hidden" name="isBlock" value="0"/>
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" value="添加"/>
                <input type="reset" class="btn btn-default" value="重设"/>
            </div>

        </div>
    </div>
    </div>
</form>
</body>
</html>
