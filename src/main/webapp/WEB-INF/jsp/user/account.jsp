<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<html>
<head>
    <title>邮箱及密码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 账户安全邮箱及密码修改 -->
<form action="${pageContext.request.contextPath}/user/account/${user.id}" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">邮箱及密码</div>
        <input type="hidden" name="_method" value="PUT">
        <input type="hidden" name="id" value="${ user.id }"/>
        <div class="panel-body" id="profileBody">
            <%--用户邮箱 --%>
            <div class="form-group" id="namePanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" value="${user.email}" placeholder="密码"/>
            </div>
            <%--用户密码 --%>
            <div class="form-group" id="namePanel">
                <label>密码</label>
                <input type="password" class="form-control" name="pass" value="${user.pass}" placeholder="密码"/>
            </div>
            <%--保存按钮 --%>
            <input type="submit" class="btn btn-primary" value="更新"/>
            <input type="reset" class="btn btn-default" value="取消"/>
        </div>
    </div>
</form>
</body>
</html>
