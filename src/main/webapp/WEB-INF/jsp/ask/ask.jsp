<%--
  Created by IntelliJ IDEA.
  User: Mo
  Date: 2016/10/3
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提问</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 提问 -->
<form action="${pageContext.request.contextPath}/discuss/add/validator">
    <div class="panel panel-default">
        <div class="panel-heading">提问</div>
        <div class="panel-body" id="searchTopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>话题标题</label>
                <input type="text" class="form-control" name="title" placeholder="话题标题"/>
            </div>

            <%--<!-- 需要对个别输入进行格式转换 -->--%>
            <%--版块id --%>
                <input type="hidden" class="form-control" name="sid" placeholder="版块id" value="2"/>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签id</label>
                <input type="text" class="form-control" name="tid" placeholder="标签id"/>
            </div>
            <%--作者id --%>
                <input type="hidden" class="form-control" name="aid" placeholder="作者id" value="${uid}"/>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <input type="textarea" class="form-control" name="content" placeholder="内容"/>
            </div>

                <!-- 剩余一些属性需初始化 -->
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" onclick="" value="提问"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
