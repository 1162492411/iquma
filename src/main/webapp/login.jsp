<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/login.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">

    <title>用户登录页面</title>
</head>
<body>
<!-- 登录框开始 -->
<div class="signin">
    <div class="signin-head">
        <img src="${pageContext.request.contextPath}/static/image/logo.png" alt="" class="img-circle"></div>
    <form class="form-signin" action="${pageContext.request.contextPath}/user/loginValidator" role="form">
        <input type="text" name="id" class="form-control" placeholder="用户名" required autofocus />
        <input type="password" name="pass" class="form-control" placeholder="密码" required />
        <button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>

    </form>
</div>
<!-- 登录框结束 -->
</body>
</html>