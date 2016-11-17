
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户详细信息页面</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        function blockUser(){
            $("#method").attr("value","POST");
            $("#uid").attr("name","uid");
            $("#userForm").submit();
        }

        function deleteUser(id){
            $.ajax({
                type: 'delete',
                url:'${pageContext.request.contextPath}/admin/user/'+id,
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("删除成功");
                        location.reload();
                    }
                    else if (data=="err"){
                        alert("未能成功删除");
                        location.reload();
                    }
                },
                error:function(data){
                }
            });
        }

    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/user/${user.id}" method="post" id="userForm">
    <input type="hidden" name="_method"  id="method" value="" />
    <div class="panel panel-default">
        <div class="panel-heading">账户详细信息</div>
        <div class="panel-body" id="profileBody">
            <!-- 用户id -->
            <div class="form-group" id="idPanel">
                <label>用户id</label>
                <input type="text" class="form-control" name="id" id="uid" value="${user.id}" placeholder="用户id"
                       readonly="readonly"/>
            </div>
            <%--用户昵称 --%>
            <div class="form-group" id="namePanel">
                <label>用户昵称</label>
                <input type="text" class="form-control" name="name" value="${user.name}" placeholder="真实姓名"/>
            </div>
            <%--用户角色id --%>
            <div class="form-group" id="ridPanel">
                <label>角色id</label>
                <input type="text" class="form-control" name="rid" value="${user.rid}" placeholder="角色id"/>
            </div>
            <%-- 邮箱--%>
            <div class="form-group" id="emailPanel">
                <label>邮箱</label>
                <input type="email" class="form-control" name="email" value="${user.email}" placeholder="安全邮箱"/>
            </div>
            <%-- 用户状态 --%>
            <div class="form-group" id="isBlockPanel">
                <label>用户状态</label>
                <input type="text" class="form-control" name="isBlock" value="${user.isBlock}" placeholder="用户状态" readonly="readonly"/>
            </div>
            <%-- 用户威望 --%>
            <div class="form-group" id="prestigePanel">
                <label>用户威望</label>
                <input type="text" class="form-control" name="prestige" value="${user.prestige}" placeholder="用户威望" readonly="readonly"/>
            </div>
            <%-- 用户获赞 --%>
            <div class="form-group" id="appCountPanel">
                <label>用户获赞</label>
                <input type="text" class="form-control" name="appCount" value="${user.appCount}" placeholder="用户获赞" readonly="readonly"/>
            </div>
            <%-- 封禁理由 --%>
            <div class="form-group" id="appCountPanel">
                <label>封禁理由</label>
                <input type="text" class="form-control" name="reason" placeholder="封禁理由,封禁用户时请填写此项"/>
            </div>
            <%-- 管理账户id --%>
            <input type="hidden" name="opid" value="${userid}"/>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <a href="/admin/user/${user.id}/update">
                <input type="button" class="btn btn-primary" value="更新"/></a>
                <input type="button" id="blockButton" class="btn btn-primary" onclick="blockUser()" value="封禁"/>
                <input type="button" id="deleteButton" class="btn btn-danger" onclick="deleteUser('${user.id}')" value="删除"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
