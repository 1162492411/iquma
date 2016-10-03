<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>重置密码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 重置密码 -->
<form action="${pageContext.request.contextPath}/user/settings/forgot/validator">
    <div class="panel panel-default">
        <div class="panel-heading">重置密码</div>
        <input type="hidden" name="id" value="${uid }"/>
        <div class="panel-body" id="profileBody">
            <%--用户邮箱 --%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" placeholder="安全邮箱"/>
            </div>
            <div class="form-control" id="newPassPanel">
                <label>新密码</label>
                <input type="password" name="pass" id="pass" placeholder="新密码"/>
            </div>
            <div class="form-control" id="confirmPassPanel">
                <label>确认密码</label>
                <input type="password" name="confirmPass" id="confirmPass" placeholder="确认密码"/>
            </div>
            <%--保存按钮 --%>
            <input type="submit" class="btn btn-primary" value="提交"/>
            <input type="reset" class="btn btn-default" value="取消"/>
        </div>
    </div>
</form>
</body>
</html>
