<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'blockUser.jsp' starting page</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function formateParameters() {
            var startTimeStr = document.getElementById("startTime").value;
            var startTime = new Date(startTimeStr);
            alert("完整开始时间为" + startTime);
            alert("待传递的开始时间的日期的天数为" + startTime.getDay());
            var endTimeStr = document.getElementById("endTime").value;
            var entTime = new Date(endTimeStr);
            alert("完整结束时间为" + endTime);
            alert("待传递的结束时间的日期的天数为" + entTime.getDay());
            document.getElementById("dlockUserForm").submit();
        }
    </script>
</head>

<body>

<!-- js存在问题，未能成功取值，但封禁用户操作成功，将封禁信息记录进数据表未测试  -->
<!-- 这里是封禁用户页面 -->
<form action="${pageContext.request.contextPath}/admin/users/block/validator" id="blockUserForm">
    <div class="panel-heading">封禁账户</div>
    <div class="panel-body" id="profileBody">
        <!-- 用户id -->
        <div class="form-group" id="idPanel">
            <label>用户id</label>
            <input type="text" name="uid" class="form-control" placeholder="用户id"/>
        </div>
        <!--操作者id -->
        <input type="hidden" name="opid" class="form-control" value="${uid}"/>
        <!-- 起始时间 -->
        <div class="form-group" id="startTimePanel">
            <label>起始时间</label>
            <input type="text" name="startTime" id="startTime" class="form-control" placeholder="起始时间"/>
        </div>
        <!-- 终止时间 -->
        <div class="form-group" id="endTimePanel">
            <label>结束时间</label>
            <input type="text" name="endTime" id="endTime" class="form-control" placeholder="结束时间"/>
        </div>
        <!-- 封禁原因 -->
        <div class="form-group" id="reasonPanel">
            <label>封禁原因</label>
            <input type="text" name="reason" class="form-control" placeholder="封禁原因"/>
        </div>
        <div id="buttonPanel">
            <input type="button" class="btn btn-primary" value="更新" onclick="formateParameters()"/>
            <input type="reset" class="btn btn-default" value="重设"/>
        </div>
    </div>


</form>
</body>
</html>
