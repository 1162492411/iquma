<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<head>
    <title>修改密码</title>
    <jsp:include page="/common/base.jsp" />
</head>
<body>
<!-- 账户安全邮箱及密码修改 -->
<form action="${pageContext.request.contextPath}/user/${user.id}/account" method="post" id="updateAccountForm">
    <div class="panel panel-default" style="position:relative;left:35%;width:30%;top:20px" >
        <div class="panel-heading">修改密码</div>
        <input type="hidden" name="_method" value="PUT">
        <input type="hidden" name="id" value="${userid}"/>
        <div class="panel-body" id="profileBody">
            <%--用户旧密码 --%>
            <div class="form-group" id="namePanel">
                <label>旧密码</label>
                <input type="password" class="form-control" name="pass" id="oldPass"  placeholder="旧密码" required />
            </div>
            <%--用户新密码 --%>
            <div class="form-group" id="namePanel">
                <label>新密码</label>
                <input type="password" class="form-control" name="pass" id="newPass"  placeholder="新密码" required />
            </div>
                <%--用户确认密码 --%>
                <div class="form-group" id="namePanel">
                    <label>确认密码</label>
                    <input type="password" class="form-control" id="confirmPass" placeholder="再次确认密码" required />
                </div>
            <%--保存按钮 --%>
            <input type="button" class="btn btn-primary" value="修改" onclick="updateAccount('${userid}')" />
            <input type="reset" class="btn btn-default" value="取消"/>
        </div>
    </div>
</form>
</body>
</html>
