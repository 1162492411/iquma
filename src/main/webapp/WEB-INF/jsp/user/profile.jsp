<%@ page language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>用户个人资料</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script>
        $(function () {
            $("#avatarFile").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
        });
    </script>
</head>
<body>
<form class="form-horizontal" id="profileForm">
    <input type="hidden" name="_method" value="PUT" />
    <div class="panel panel-default" style="position:relative;left:25%;width:50%;top:20px">
        <div class="panel-heading">个人档案</div>
        <input type="hidden" name="id" value="${ user.id }"/>
        <div class="panel-body" id="profileBody">
            <%-- 头像 --%>
                <div class="form-group" id="avatarPanel">
                    <div class="thumbnail col-md-offset-3 col-md-6">
                        <div><img id="ImgPr" width="120" height="120" src="${user.avatar}"/></div>
                        <div class="caption">
                            <input id="avatarFile" class="btn" value="选择文件" type="file" accept="image/jpeg">
                            <input class="btn btn-primary" type="button" id="uploadAvatarButton" value="上传头像" onclick="uploadAvatar()" >
                        </div>
                    </div>
                </div>
<%-- 昵称 --%>
            <div class="form-group" id="namePanel">
                <label for="name" class="col-md-offset-2 col-md-2 control-label">昵称</label>
                <div class="col-md-5">
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="账户昵称" />
                </div>
            </div>
            <%-- 简介 --%>
            <div class="form-group" id="descriptionPanel">
                <label for="description" class="col-md-offset-2 col-md-2 control-label">简介</label>
                <div class="col-md-5">
                    <input type="text" class="form-control" id="description" name="description" value="${user.description}" placeholder="账户简介" />
                </div>
            </div>
            <%-- 邮箱 --%>
            <div class="form-group" id="emailPanel">
                <label for="email" class="col-md-offset-2 col-md-2 control-label">邮箱</label>
                <div class="col-md-5">
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="安全邮箱" />
                </div>
            </div>
            <%-- 按钮 --%>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-4">
                    <button type="button" class="btn btn-primary" onclick="updateProfile(${userid})">保存</button>
                    <button type="reset" class="btn btn-default">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
