<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提问</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(function () {
            initTypeId();
            $("#typeIdSelection").change(function () {
                updateTidSelection($('#typeIdSelection option:selected').val());
            });
        });
    </script>
</head>
<body>
<!-- 提问 -->
<form action="${pageContext.request.contextPath}/ask" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <div class="panel panel-default">
        <div class="panel-heading">提问</div>
        <div class="panel-body" id="TopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>话题标题</label>
                <input type="text" class="form-control" name="title" placeholder="话题标题"/>
            </div>
            <%--类别id --%>
            <div class="form-group" id="typeIdPanel">
                <label>类别</label>
                <select id="typeIdSelection">
                </select>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签id</label>
                <select name="tid" id="tidSelection">
                </select>
            </div>
            <%--作者id --%>
            <input type="hidden" name="aid" value="${userid}"/>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <textarea name="content" cols="120" rows="5" placeholder="写点什么吧"></textarea>
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
