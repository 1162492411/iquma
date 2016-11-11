<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分享代码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 提问 -->
<form action="${pageContext.request.contextPath}/upload" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <div class="panel panel-default">
        <div class="panel-heading">分享代码</div>
        <div class="panel-body" id="TopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>代码标题</label>
                <input type="text" class="form-control" name="title" placeholder="话题标题"/>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签id</label>
                <input type="text" class="form-control" name="tid" placeholder="标签id"/>
            </div>
            <%--作者id --%>
            <input type="hidden" name="aid" value="${userid}"/>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <input type="textarea" class="form-control" name="content" placeholder="内容"/>
            </div>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" value="提问"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
