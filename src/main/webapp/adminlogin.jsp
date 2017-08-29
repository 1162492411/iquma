<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>管理员登录页面</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/login.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
</head>
<body>
<!-- 登录框开始 -->
<div id="login-part">
    <form action="${pageContext.request.contextPath}/login/admin/validator" method="post"
          style="height: 400px; width: 250px; margin-left: 50px; margin-top: 20px">
        <div class="form-group">
            <input class="form-control" name="id" required="" placeholder="账户"
                   type="text">
        </div>
        <div class="form-group">
            <input class="form-control" name="password" required=""
                   placeholder="密码" type="password">
        </div>
        <div class="form-group clearfix">
            <button type="submit" class="btn btn-primary pull-right pl20 pr20">登录</button>
        </div>
    </form>
</div>
<!-- 登录框结束 -->
</body>
</html>