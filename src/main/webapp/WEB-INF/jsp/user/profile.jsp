<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户个人资料</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 个人基础信息显示和编辑 -->
<form action="${pageContext.request.contextPath}/user/profile/edit">
    <div class="panel panel-default">
        <div class="panel-heading">个人档案</div>
        <input type="hidden" name="id" value="${ uid }"/>
        <div class="panel-body" id="profilebody">
            <!-- 用户头像 -->
            <div class="form-group" id="avatarpanel">
                <label>用户头像</label><br/>
                <img class="img_thumbnail" alt="用户头像" src="http://ocgfh1n3q.bkt.clouddn.com/default_avatar.png"/>
                <input type="file" id="avatat_upload">
            </div>
            <%--用户姓名 --%>
            <div class="form-group" id="namepanel">
                <label>姓名</label>
                <input type="text" class="form-control" name="name" placeholder="真实姓名">
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailpanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" placeholder="安全邮箱">
            </div>
            <%--保存按钮 --%>
            <input type="submit" class="btn btn-primary" value="更新信息"/>
            <input type="reset" class="btn btn-default" value="取消">
        </div>
    </div>
</form>
</body>
</html>
