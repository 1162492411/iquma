<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%-- 用户具有修改主贴的权限时显示页面 --%>
    <shiro:hasPermission name="${topic.sec}:update">
        <form id="updateTopicForm" >
            <div class="panel panel-default">
                <div class="panel-heading">修改教程</div>
                <div class="panel-body" id="topicPanel">
                    <input type="hidden" name="id" id="id" value="${topic.id}" />
                    <input type="hidden" name="aid" id="aid" value="${topic.aid}" />
                    <input type="hidden" name="topic.sec" id="sectionname" value="${topic.sec}" />
                    <%--主贴标题 --%>
                    <div class="form-group" id="titlePanel">
                        <label>主贴标题</label>
                        <input type="text" class="form-control" name="title" id="title" value="${topic.title}" placeholder="话题标题"/>
                    </div>
                    <%-- 内容--%>
                    <div class="form-group" id="contentPanel">
                        <label>主贴内容</label>
                        <input type="textarea" class="form-control" name="content"  id="content" value="${topic.content}"  placeholder="内容"/>
                    </div>
                    <!-- 按钮 -->
                    <div class="form-group" id="buttonPanel">
                        <input type="button" id="updateButton" class="btn btn-primary" onclick="updateTopic('${topic.sec}')"  value="修改"/>
                        <input type="reset" class="btn btn-default" value="重置"/>
                    </div>
                </div>
            </div>
            </div>
        </form>
    </shiro:hasPermission>
    <%-- 用户没有修改该主贴的权限时 --%>
    <shiro:lacksPermission name="${topic.sec}:update">
        <%-- 如果该贴是用户自己的主贴时显示页面内容 --%>
        <c:if test="${topic.aid eq userid}">
            <form id="updateTopicForm" >
                <div class="panel panel-default">
                    <div class="panel-heading">修改教程</div>
                    <div class="panel-body" id="topicPanel">
                        <input type="hidden" name="id" id="id" value="${topic.id}" />
                        <input type="hidden" name="aid" id="aid" value="${topic.aid}" />
                        <input type="hidden" name="topic.sec" id="sectionname" value="${topic.sec}" />
                            <%--主贴标题 --%>
                        <div class="form-group" id="titlePanel">
                            <label>主贴标题</label>
                            <input type="text" class="form-control" name="title" id="title" value="${topic.title}" placeholder="话题标题"/>
                        </div>
                            <%-- 内容--%>
                        <div class="form-group" id="contentPanel">
                            <label>主贴内容</label>
                            <input type="textarea" class="form-control" name="content"  id="content" value="${topic.content}"  placeholder="内容"/>
                        </div>
                        <!-- 按钮 -->
                        <div class="form-group" id="buttonPanel">
                            <input type="button" id="updateButton" class="btn btn-primary" onclick="updateTopic('${topic.sec}')"  value="修改"/>
                            <input type="reset" class="btn btn-default" value="重置"/>
                        </div>
                    </div>
                </div>
                </div>
            </form>
        </c:if>
        <%-- 如果该帖不是用户自己的主贴时提示用户 --%>
        <c:if test="${topic.aid != userid}">
            <div class="panel panel-info">
                <div class="panel-heading">提示</div>
                <div class="panel-body">
                    抱歉，你并不能修改该教程!
                </div>
            </div>
        </c:if>
    </shiro:lacksPermission>
</body>
</html>
