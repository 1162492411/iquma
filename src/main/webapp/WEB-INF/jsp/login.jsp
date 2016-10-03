<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <title>用户登录页面</title>
</head>
<body>
<!-- 登录框开始 -->
<div id="login-part">
    <form action="${pageContext.request.contextPath}/login/validator" method="post"
          style="height: 400px; width: 250px; margin-left: 50px; margin-top: 20px">
        <div class="form-group">
            <input class="form-control" name="id" required="" placeholder="学号"
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