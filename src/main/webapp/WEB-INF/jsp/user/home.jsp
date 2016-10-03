<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'home.jsp' starting page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>

<body>
<!-- 这里是用户主页 -->


<!-- 显示用户简略信息，最近动态，收藏内容 -->
<!-- 个人基础信息显示和编辑 -->
<form action="${pageContext.request.contextPath}/user/settings/profile/validator">
    <div class="panel panel-default">
        <div class="panel-heading">个人档案</div>
        <input type="hidden" name="id" value="${ user.id }"/>
        <div class="panel-body" id="profileBody">
            <!-- 用户头像 -->
            <div class="form-group" id="avatarPanel">
                <label>头像</label><br/>
                <img class="img_thumbnail" alt="用户头像" src="${user.avatar}"/>
            </div>
            <%--用户姓名 --%>
            <div class="form-group" id="namePanel">
                <label>昵称</label>
                <input type="text" class="form-control" name="name" value="${user.name}" placeholder="真实姓名"
                       readonly="readonly"/>
            </div>
            <%--用户角色id --%>
            <div class="form-group" id="ridPanel">
                <label>等级</label>
                <input type="text" class="form-control" name="rid" value="${user.rid}" placeholder="角色id"
                       readonly="readonly"/>
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" value="${user.email}" placeholder="安全邮箱"
                       readonly="readonly"/>
            </div>
            <%-- 账户状态--%>
            <div class="form-group" id="isBlockPanel">
                <label>状态</label>
                <input type="text" class="form-control" name="isBlock" value="${user.isBlock}" placeholder="账户状态"
                       readonly="readonly"/>
            </div>
            <%-- 账户威望--%>
            <div class="form-group" id="prestigePanel">
                <label>威望</label>
                <input type="text" class="form-control" name="prestige" value="${user.prestige}" placeholder="账户威望"
                       readonly="readonly"/>
            </div>
            <%-- 账户获赞--%>
            <div class="form-group" id="appPanel">
                <label>获赞</label>
                <input type="text" class="form-control" name="appCount" value="${user.appCount}" placeholder="账户获赞"
                       readonly="readonly"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>
