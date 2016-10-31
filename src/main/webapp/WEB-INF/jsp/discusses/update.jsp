<%--
  Created by IntelliJ IDEA.
  User: Mo
  Date: 2016/10/3
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
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
<!-- 修改提问 -->
<form action="${pageContext.request.contextPath}/discuss/update/validator">
    <div class="panel panel-default">
        <div class="panel-heading">修改提问</div>
        <div class="panel-body" id="searchTopicBody">
            <input type="hidden" name="id" value="${topic.id}" />
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>话题标题</label>
                <input type="text" class="form-control" name="title" value="${topic.title}" placeholder="话题标题"/>
            </div>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <input type="textarea" class="form-control" name="content"  value="${topic.content}"  placeholder="内容"/>
            </div>
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" onclick="" value="修改"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
