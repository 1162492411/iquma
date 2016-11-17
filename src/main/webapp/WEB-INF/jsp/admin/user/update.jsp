
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新用户</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(function () {
            initRid();
        });
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/user/${user.id}/update" method="post" id="userForm">
    <input onclick="initRid('${pageContext.request.contextPath}')" />
    <input type="hidden" name="_method"  id="method" value="PUT" />
    <div class="panel panel-default">
        <div class="panel-heading">更新账户</div>
        <div class="panel-body" id="profileBody">
            <%-- 用户id --%>
            <div class="form-group" id="idPanel">
                <label>用户id</label>
                <input type="text" class="form-control" name="id" id="uid" value="${user.id}" placeholder="用户id" readonly />
            </div>
            <%--用户昵称 --%>
            <div class="form-group" id="namePanel">
                <label>用户昵称</label>
                <input type="text" class="form-control" name="name" value="${user.name}" placeholder="真实姓名"/>
            </div>
            <%--原角色id --%>
            <div class="form-group">
                <label>原角色id</label>
                <input type="text" class="form-control" value="${user.rid}" readonly />
            </div>
                <%-- 新角色id--%>
                <div class="form-group">
                    <label>新角色id</label>
                    <select name="rid" id="ridSelection"></select>
                </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" value="${user.email}" placeholder="安全邮箱"/>
            </div>
            <%-- 用户状态 --%>
            <div class="form-group" id="isBlockPanel">
                <label>用户状态</label>
                <input type="text" class="form-control" name="isBlock" value="${user.isBlock}" placeholder="用户状态" readonly />
            </div>
            <%-- 用户威望 --%>
            <div class="form-group" id="prestigePanel">
                <label>用户威望</label>
                <input type="text" class="form-control" name="prestige" value="${user.prestige}" placeholder="用户威望" readonly />
            </div>
            <%-- 用户获赞 --%>
            <div class="form-group" id="appCountPanel">
                <label>用户获赞</label>
                <input type="text" class="form-control" name="appCount" value="${user.appCount}" placeholder="用户获赞" readonly />
            </div>
            <%-- 管理账户id --%>
            <input type="hidden" name="opid" value="${userid}"/>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" value="更新"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
