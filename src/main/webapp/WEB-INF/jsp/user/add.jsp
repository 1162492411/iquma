<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>添加用户</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
</head>

<body>
<!-- 添加账户 -->
<!-- 若权限不足，则显示空白~ -->
<shiro:lacksPermission name="suer:create">

</shiro:lacksPermission>
<!-- 若用户具有足够权限,则显示添加账户页面 -->
<shiro:hasPermission name="suser:create">
<form>
    <div class="panel panel-default">
        <div class="panel-heading">添加账户</div>
        <div class="panel-body" id="profileBody">
            <!-- 用户id -->
            <div class="form-group" id="idPanel">
                <label>用户id</label>
                <input type="text" class="form-control" id="id" placeholder="用户id"/>
            </div>
            <%--用户昵称 --%>
            <div class="form-group" id="namePanel">
                <label>用户昵称</label>
                <input type="text" class="form-control" id="name" placeholder="真实姓名"/>
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" id="email" placeholder="安全邮箱"/>
            </div>
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
    <shiro:hasPermission name="suser:create">
                <input type="button" class="btn btn-primary" onclick="addUser('Student')" value="添加学生"/>
    </shiro:hasPermission>
                <shiro:hasPermission name="muser:create">
                    <input type="button" class="btn btn-primary" onclick="addUser('Teacher')" value="添加教师"/>
                </shiro:hasPermission>
                <input type="reset" class="btn btn-default" value="重设"/>
            </div>
        </div>
    </div>
    </div>
</form>
</shiro:hasPermission>
</body>
</html>
