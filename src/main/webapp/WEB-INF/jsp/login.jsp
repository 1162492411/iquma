<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <title>用户登录页面</title>
</head>
<body>
<!-- 登录框开始 -->
<div id="login-part">
    <form action="${pageContext.request.contextPath}/user/login" method="post"
          style="height: 400px; width: 250px; margin-left: 50px; margin-top: 20px">
        <input type="hidden" name="_method" value="POST"/>
        <!-- 学号-->
        <div class="form-group">
            <input type="text" class="form-control" name="id" placeholder="学号" required=""
            />
        </div>
        <!-- 密码 -->
        <div class="form-group">
            <input type="password" class="form-control" name="pass"
                   placeholder="密码" required=""/>
        </div>
        <!-- 登录按钮 -->
        <div class="form-group clearfix">
            <button type="submit" class="btn btn-primary pull-right pl20 pr20">登录</button>
        </div>

    </form>
</div>
<!-- 登录框结束 -->
</body>
</html>