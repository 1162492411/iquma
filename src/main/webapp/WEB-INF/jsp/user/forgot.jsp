<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<head>
    <title>重置密码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 根据返回信息提示用户是否操作成功 -->
<!-- 重置密码 -->
<form action="${pageContext.request.contextPath}/user/${user.id}/forgot" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">重置密码</div>
        <input type="hidden" name="_method" value="POST"/>
        <input type="hidden" name="id" value="${user.id }"/>
        <div class="panel-body" id="profileBody">
            <%--用户邮箱 --%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" placeholder="安全邮箱"/>
            </div>
            <div class="form-group" id="newPassPanel">
                <label>新密码</label>
                <input type="password" class="form-control" name="pass" id="pass" placeholder="新密码"/>
            </div>
            <div class="form-group" id="confirmPassPanel">
                <label>确认密码</label>
                <input type="password" class="form-control" name="confirmPass" id="confirmPass" placeholder="确认密码"/>
            </div>
            <%--保存按钮 --%>
            <input type="submit" class="btn btn-primary" value="提交"/>
            <input type="reset" class="btn btn-default" value="取消"/>
        </div>
    </div>
</form>
</body>
</html>
