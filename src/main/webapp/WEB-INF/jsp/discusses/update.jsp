
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改提问</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 根据返回信息提示用户是否更新成功 -->
<!-- 修改提问 -->
<form action="${pageContext.request.contextPath}/discuss/${discuss.id}/update" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">修改提问</div>
        <div class="panel-body" id="discussPanel">
            <input type="hidden" name="_method" value="PUT"/>
            <input type="hidden" name="id" value="${discuss.id}" />
            <%--主贴标题 --%>
            <div class="form-group" id="titlePanel">
                <label>主贴标题</label>
                <input type="text" class="form-control" name="title" id="title" value="${discuss.title}" placeholder="话题标题"/>
            </div>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>主贴内容</label>
                <input type="textarea" class="form-control" name="content"  value="${discuss.content}"  placeholder="内容"/>
            </div>
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit"  id="updateButton" class="btn btn-primary" value="修改"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
