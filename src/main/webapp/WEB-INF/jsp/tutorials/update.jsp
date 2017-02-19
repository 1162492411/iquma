<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>修改教程</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
</head>
<body>
<%-- 修改教程 --%>
    <%-- 用户具有修改该主贴的权限时 --%>
    <shiro:hasPermission name="tutorial:update:${tutorial.id}">
        <! -- action="/tutorial/${tutorial.id}/update" -->
        <form id="updateTutorialForm" action="/tutorial/${tutorial.id}/update" method="post">
            <%--<input type="hidden" name="_method" value="PUT" />--%>
            <div class="panel panel-default">
                <div class="panel-heading">修改教程</div>
                <div class="panel-body" id="tutorialPanel">
                    <input type="hidden" name="id" value="${tutorial.id}" />
                    <%--主贴标题 --%>
                    <div class="form-group" id="titlePanel">
                        <label>主贴标题</label>
                        <input type="text" class="form-control" name="title" id="title" value="${tutorial.title}" placeholder="话题标题"/>
                    </div>
                    <%-- 内容--%>
                    <div class="form-group" id="contentPanel">
                        <label>主贴内容</label>
                        <input type="textarea" class="form-control" name="content"  value="${tutorial.content}"  placeholder="内容"/>
                    </div>
                    <!-- 按钮 -->
                    <div class="form-group" id="buttonPanel">
                        <input type="button" id="updateButton" class="btn btn-primary" onclick="updateTopic('tutorial')"  value="修改"/>
                        <input type="reset" class="btn btn-default" value="重置"/>
                    </div>
                </div>
            </div>
            </div>
        </form>
    </shiro:hasPermission>
    <%-- 用户没有修改该主贴的权限时 --%>
    <shiro:lacksPermission name="tutorial:update:${tutorial.id}">
        抱歉，你并不能修改该教程!
    </shiro:lacksPermission>
</body>
</html>
