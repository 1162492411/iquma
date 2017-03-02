<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
</head>
<!-- 根据返回信息提示用户是否操作成功 -->
<body>
<!-- 账户安全邮箱及密码修改 -->
<form action="${pageContext.request.contextPath}/user/${user.id}/account" method="post" id="updateAccountForm">
    <div class="panel panel-default">
        <div class="panel-heading">修改密码</div>
        <input type="hidden" name="_method" value="PUT">
        <input type="hidden" name="id" value="${ user.id }"/>
        <div class="panel-body" id="profileBody">
            <%--用户新密码 --%>
            <div class="form-group" id="namePanel">
                <label>新密码</label>
                <input type="password" class="form-control" name="pass" id="newPass"  placeholder="新密码" required />
            </div>
                <%--用户确认密码 --%>
                <div class="form-group" id="namePanel">
                    <label>确认密码</label>
                    <input type="password" class="form-control" id="confirmPass" placeholder="请再次输入" required />
                </div>
            <%--保存按钮 --%>
            <input type="button" class="btn btn-primary" value="更新" onclick="updateAccount(${userid})" />
            <input type="reset" class="btn btn-default" value="取消"/>
        </div>
    </div>
</form>
</body>
</html>
